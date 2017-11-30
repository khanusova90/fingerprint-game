package cz.hanusova.fingerprintgame.reader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cz.hanusova.fingerprintgame.fingerprint.BleScan;
import cz.hanusova.fingerprintgame.fingerprint.Fingerprint;

public class FingerprintReader {

	private static final String FILENAME = "fingerprints.json";
	private static final String BEACON_FILE = "beacons.json";

	public void readFile() {
		ClassLoader loader = getClass().getClassLoader();
		URL resource = loader.getResource(FILENAME);
		String filepath = resource.getFile();
		File fingerprintFile = new File(filepath);
		File beaconFile = new File(loader.getResource(BEACON_FILE).getFile());

		ObjectMapper mapper = new ObjectMapper();
		try {
			List<FingerprintEnvelope> fingerprints = mapper.readValue(fingerprintFile,
					new TypeReference<List<FingerprintEnvelope>>() {
					});
			System.out.println(fingerprints.size());
			List<Beacon> beacons = mapper.readValue(beaconFile, new TypeReference<List<Beacon>>() {
			});
			System.out.println(beacons.size());

			List<FingerprintEnvelope> filteredFingerprints = fingerprints.stream()
					.filter(envelope -> envelope.getBeacon().getWifiScans().stream().noneMatch(
							wifi -> wifi.getSSID().equals("Tobola HKFree") || wifi.getSSID().equals("Doma-Hanusovi")))
					.collect(Collectors.toList());
			List<String> beaconMacs = beacons.stream().filter(Objects::nonNull).map(Beacon::getMac)
					.collect(Collectors.toList());

			System.out.println(filteredFingerprints.size());

			HashMap<String, List<Fingerprint>> fingerprintMap = new HashMap<>();
			for (FingerprintEnvelope e : filteredFingerprints) {
				Fingerprint f = e.getBeacon();
				String key = "J" + f.getLevel() + "NP_" + f.getX() + "-" + f.getY();
				if (key.equals("J1NP_560-825") || key.equals("J3NP_220-400")) {
					continue;
				}

				List<BleScan> scans = f.getBleScans().stream()
						.filter(b -> b != null && beaconMacs.contains(b.getAddress())).collect(Collectors.toList());
				f.setBleScans(scans);

				if (fingerprintMap.containsKey(key)) {
					fingerprintMap.get(key).add(f);
				} else {
					List<Fingerprint> mapFingerprints = new ArrayList<>();
					mapFingerprints.add(f);
					fingerprintMap.put(key, mapFingerprints);
				}
			}

			System.out.println(fingerprintMap.keySet().size());
			ExcelCreator creator = new ExcelCreator();
			creator.createExcelFiles(fingerprintMap);
			creator.createFingerprintMap(beacons);
			System.out.println("DONE");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
