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

	@Value("${file.fingerprint.name}")
	private String fileName;
	@Value("${file.fingerprint.path}")
	private String path;

	@Override
	public void saveFingerprint(Fingerprint fingerprint) {
		Date now = new Date();
		String name = path + fileName + SEPARATOR + SDF.format(now);
		logger.info("Saving new fingerprint to file " + name);
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(name))) {
			writer.write(fingerprint.toString());
			logger.info("Fingerprint successfully saved");
		} catch (IOException e) {
			logger.error("Could not save fingerprint to file", e);
		}

	}

}
