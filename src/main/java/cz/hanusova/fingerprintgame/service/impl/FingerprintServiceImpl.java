/**
 * 
 */
package cz.hanusova.fingerprintgame.service.impl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.SerializableDocument;

import cz.hanusova.fingerprintgame.fingerprint.Fingerprint;
import cz.hanusova.fingerprintgame.service.FingerprintService;

/**
 * Service class for handling fingerprints
 * 
 * @author khanusova
 *
 */
@Service
public class FingerprintServiceImpl implements FingerprintService {
	private static final Log logger = LogFactory.getLog(FingerprintServiceImpl.class);

	private static final SimpleDateFormat SDF = new SimpleDateFormat("ddMMyyyy_HHmmssSSS");
	private static final String SEPARATOR = "_";
	private static final String ID_NAME = "fingerprint";
	private static final String APP_NAME = "fingerprint-game";

	private static int fingerprint_id = 0;

	@Value("${file.fingerprint.name}")
	private String fileName;
	@Value("${file.fingerprint.path}")
	private String path;
	@Value("${profile.name}")
	private String profile;

	@Override
	public void saveFingerprint(Fingerprint fingerprint) {
		fingerprint.setAppName(APP_NAME);
		Date now = new Date();
		String name = fileName + SEPARATOR + SDF.format(now);
		saveToFile(fingerprint, name);
		if (profile.equals("production")) {
			saveToCouchbase(fingerprint, name);
		}
	}

	/**
	 * Saves fingerprint to new separate file
	 * 
	 * @param fingerprint
	 */
	private void saveToFile(Fingerprint fingerprint, String fileName) {
		String name = path + fileName;
		logger.info("Saving new fingerprint to file " + name);
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(name))) {
			writer.write(fingerprint.toString());
			logger.info("Fingerprint successfully saved");
		} catch (IOException e) {
			logger.error("Could not save fingerprint to file", e);
		}
	}

	private void saveToCouchbase(Fingerprint fingerprint, String fileName) {
		CouchbaseCluster cluster = CouchbaseCluster.create();
		// try {
		// ObjectMapper mapper = new ObjectMapper();
		// String fpString = mapper.writeValueAsString(fingerprint);
		Bucket bucket = cluster.openBucket("beacon");
		bucket.upsert(SerializableDocument.create(fileName, fingerprint));
		fingerprint_id++;
		// } catch (JsonProcessingException e) {
		// logger.error("could not deserialize fingerprint", e);
		// }
		cluster.disconnect();
	}

}
