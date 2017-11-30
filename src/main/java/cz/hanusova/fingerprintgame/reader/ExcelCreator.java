package cz.hanusova.fingerprintgame.reader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cz.hanusova.fingerprintgame.fingerprint.BleScan;
import cz.hanusova.fingerprintgame.fingerprint.CellScan;
import cz.hanusova.fingerprintgame.fingerprint.Fingerprint;
import cz.hanusova.fingerprintgame.fingerprint.WifiScan;

public class ExcelCreator {

	private static final String filePath = "/Volumes/Macintosh SSD/Users/khanusova/Documents/fingerprints/";
	private static final String SUFFIX = ".xlsx";

	private Statistics statistics;

	public void createFingerprintMap(List<Beacon> beacons) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		Row descriptionRow = sheet.createRow(0);
		Cell number = descriptionRow.createCell(0);
		number.setCellValue("Number");
		Cell mac = descriptionRow.createCell(1);
		mac.setCellValue("MAC");
		for (int i = 1; i < beacons.size(); i++) {
			Row row = sheet.createRow(i);
			Cell numberCell = row.createCell(0);
			numberCell.setCellValue(i);
			Cell macCell = row.createCell(1);
			macCell.setCellValue(beacons.get(i).getMac());
		}
		try {
			File outputFile = new File(filePath + "beacons" + SUFFIX);
			if (outputFile.exists()) {
				outputFile.delete();
			}
			FileOutputStream os = new FileOutputStream(outputFile);
			workbook.write(os);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createExcelFiles(HashMap<String, List<Fingerprint>> fingerprintMap) {
		for (String key : fingerprintMap.keySet()) {
			System.out.println("Creating " + key);
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet summarySheet = workbook.createSheet("summary");
			// XSSFSheet cellSheet = workbook.createSheet("cellScans");
			XSSFSheet bleSheet = workbook.createSheet("bleScans");
			XSSFSheet wifiSheet = workbook.createSheet("wifiScans");
			XSSFSheet bleRssi = workbook.createSheet("bleRssi");
			XSSFSheet wifiRssi = workbook.createSheet("wifiRssi");
			XSSFSheet bleTimeSheet = workbook.createSheet("bleTime");
			XSSFSheet wifiTimeSheet = workbook.createSheet("wifiTime");
			Map<String, Integer> bleMacMap = new HashMap<>();
			Map<String, Integer> wifiMacMap = new HashMap<>();

			int sumRowNum = 0;
			// int cellRowNum = 0;
			int bleRowNum = 0;
			int wifiRowNum = 0;

			Row bleRssiRow = bleRssi.createRow(bleRowNum);
			Row wifiRssiRow = wifiRssi.createRow(wifiRowNum);
			Row summaryRow = summarySheet.createRow(sumRowNum++);
			// Row cellRow = cellSheet.createRow(cellRowNum++);
			Row bleRow = bleSheet.createRow(bleRowNum++);
			Row wifiRow = wifiSheet.createRow(wifiRowNum++);
			createBleRssiHeaders(bleRssiRow);
			createWifiRssiHeaders(wifiRssiRow);
			createSummaryHeaders(summaryRow);
			// createCellScanHeaders(cellRow);
			createBleHeaders(bleRow);
			createWifiHeaders(wifiRow);
			List<Fingerprint> fingerprints = fingerprintMap.get(key);
			statistics = new Statistics(fingerprints);
			for (Fingerprint fingerprint : fingerprints) {
				String deviceName = fingerprint.getBrand() + " " + fingerprint.getModel();
				createSummaryRow(sumRowNum++, summarySheet, fingerprint);
				// List<CellScan> cellScans = fingerprint.getCellScans();
				// if (!cellScans.isEmpty()) {
				// createCellScanRow(cellRowNum++, cellSheet, cellScans,
				// fingerprint.getBrand() + " " + fingerprint.getModel());
				// }
				List<BleScan> bleScans = fingerprint.getBleScans();
				if (!bleScans.isEmpty()) {
					createBleRssiRow(bleRowNum, bleRssi, deviceName, bleScans, bleMacMap);
					createBleRow(bleRowNum++, bleSheet, bleScans, deviceName);
				}
				List<WifiScan> wifiScans = fingerprint.getWifiScans();
				if (!wifiScans.isEmpty()) {
					createWifiRssiRow(wifiRowNum, wifiRssi, deviceName, wifiScans, wifiMacMap);
					createWifiRow(wifiRowNum++, wifiSheet, wifiScans, deviceName);
				}
			}
			createSummaryFooter(sumRowNum, summarySheet);
			createBleTimeRow(bleTimeSheet, fingerprints);
			createWifiTimeRow(wifiTimeSheet, fingerprints);

			try {
				File outputFile = new File(filePath + key + SUFFIX);
				if (outputFile.exists()) {
					outputFile.delete();
				}
				FileOutputStream os = new FileOutputStream(outputFile);
				workbook.write(os);
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void createSummaryHeaders(Row row) {
		int colNum = 0;
		Cell device = row.createCell(colNum++);
		device.setCellValue("Device");
		Cell cells = row.createCell(colNum++);
		cells.setCellValue("Cell scans");
		Cell bles = row.createCell(colNum++);
		bles.setCellValue("BLE scans");
		Cell wifis = row.createCell(colNum++);
		wifis.setCellValue("WiFi scans");
		Cell cellU = row.createCell(colNum++);
		cellU.setCellValue("Unique cell scans");
		Cell bleU = row.createCell(colNum++);
		bleU.setCellValue("Unique BLE scans");
		Cell wifiU = row.createCell(colNum++);
		wifiU.setCellValue("Uniquie WiFi scans");
	}

	private void createSummaryRow(int rowNum, XSSFSheet sheet, Fingerprint fingerprint) {
		Row row = sheet.createRow(rowNum);
		int colNum = 0;
		Cell device = row.createCell(colNum++);
		device.setCellValue(fingerprint.getBrand() + " " + fingerprint.getModel());
		Cell cells = row.createCell(colNum++);
		cells.setCellValue(fingerprint.getCellScans().size());
		Cell bles = row.createCell(colNum++);
		bles.setCellValue(fingerprint.getBleScans().size());
		Cell wifis = row.createCell(colNum++);
		wifis.setCellValue(fingerprint.getWifiScans().size());
		Cell cellU = row.createCell(colNum++);
		cellU.setCellValue(statistics.getUniqueCellCount(fingerprint.getCellScans()));
		Cell bleU = row.createCell(colNum++);
		bleU.setCellValue(statistics.getUniqueBleCount(fingerprint.getBleScans()));
		Cell wifiU = row.createCell(colNum++);
		wifiU.setCellValue(statistics.getUniqueWifiCount(fingerprint.getWifiScans()));
	}

	private void createSummaryFooter(int rowNum, XSSFSheet sheet) {
		statistics.countStatistics();
		createMeanRow(rowNum++, sheet);
		createMeanWithoutZerosRow(rowNum++, sheet);
		createModeRow(rowNum++, sheet);
		createModeWithoutZerosRow(rowNum++, sheet);
		createMedianRow(rowNum++, sheet);
		createMedianWithoutZerosRow(rowNum++, sheet);
	}

	private void createBleRssiHeaders(Row row) {
		int colNum = 0;
		Cell device = row.createCell(colNum++);
		device.setCellValue("Device");
	}

	private void createWifiRssiHeaders(Row row) {
		int colNum = 0;
		Cell device = row.createCell(colNum++);
		device.setCellValue("Device");
	}

	private void createBleRssiRow(int rowNum, XSSFSheet sheet, String deviceName, List<BleScan> scans,
			Map<String, Integer> macMap) {
		Row row = sheet.createRow(rowNum);
		int colNum = 0;
		Cell device = row.createCell(colNum++);
		device.setCellValue(deviceName);
		Map<String, List<Integer>> scanMap = new HashMap<>();
		for (BleScan scan : scans) {
			if (scan != null) {
				if (scanMap.containsKey(scan.getAddress())) {
					scanMap.get(scan.getAddress()).add(scan.getRssi());
				} else {
					List<Integer> rssiList = new ArrayList<>();
					rssiList.add(scan.getRssi());
					scanMap.put(scan.getAddress(), rssiList);
				}
			}
		}
		for (String key : scanMap.keySet()) {
			double med = statistics.countMedian(scanMap.get(key));
			int macCol;
			if (macMap.containsKey(key)) {
				macCol = macMap.get(key);
			} else {
				macCol = macMap.size() + 1;
				macMap.put(key, macCol);
				Row nameRow = sheet.getRow(0);
				Cell nameCell = nameRow.createCell(macCol);
				nameCell.setCellValue(key);
			}
			Cell cell = row.createCell(macCol);
			cell.setCellValue(med);
		}
	}

	private void createWifiRssiRow(int rowNum, XSSFSheet sheet, String deviceName, List<WifiScan> scans,
			Map<String, Integer> macMap) {
		Row row = sheet.createRow(rowNum);
		int colNum = 0;
		Cell device = row.createCell(colNum++);
		device.setCellValue(deviceName);
		Map<String, List<Integer>> scanMap = new HashMap<>();
		for (WifiScan scan : scans) {
			if (scan != null) {
				String key = scan.getMAC();
				if (scanMap.containsKey(key)) {
					scanMap.get(key).add(scan.getStrength());
				} else {
					List<Integer> rssiList = new ArrayList<>();
					rssiList.add(scan.getStrength());
					scanMap.put(key, rssiList);
				}
			}
		}
		for (String key : scanMap.keySet()) {
			double med = statistics.countMedian(scanMap.get(key));
			int macCol;
			if (macMap.containsKey(key)) {
				macCol = macMap.get(key);
			} else {
				macCol = macMap.size() + 1;
				macMap.put(key, macCol);
				Row nameRow = sheet.getRow(0);
				Cell nameCell = nameRow.createCell(macCol);
				nameCell.setCellValue(key);
			}
			Cell cell = row.createCell(macCol);
			cell.setCellValue(med);
		}
	}

	private void createBleTimeRow(XSSFSheet sheet, List<Fingerprint> fingerprints) {
		Map<String, List<Fingerprint>> fingerprintMap = new HashMap<>();
		for (Fingerprint fingerprint : fingerprints) {
			String device = fingerprint.getDeviceID();
			if (fingerprintMap.containsKey(device)) {
				fingerprintMap.get(device).add(fingerprint);
			} else {
				ArrayList<Fingerprint> devFingerprints = new ArrayList<>();
				devFingerprints.add(fingerprint);
				fingerprintMap.put(device, devFingerprints);
			}
		}
		int rowNum = 0;
		int colNum = 0;
		Row descRow = sheet.createRow(rowNum++);
		Cell name = descRow.createCell(colNum++);
		name.setCellValue("Device");
		createMsRow(descRow);

		for (String device : fingerprintMap.keySet()) {
			List<Fingerprint> devFingerprints = fingerprintMap.get(device);
			if (devFingerprints.size() < 5) {
				continue;
			}
			// Map<Integer, List<Integer>> timeMap = new HashMap<>();
			for (Fingerprint fingerprint : devFingerprints) {
				Row row = sheet.createRow(rowNum++);
				colNum = 0;
				Cell deviceCell = row.createCell(colNum++);
				deviceCell.setCellValue(fingerprint.getBrand() + " " + fingerprint.getModel());

				Set<String> transmitterSet = new HashSet<>();
				List<BleScan> scans = fingerprint.getBleScans();
				Collections.sort(scans, new BleComparator());
				for (int i = colNum; i <= 10001; i++) {
					Cell num = row.createCell(i);
					num.setCellValue(0);
				}
				for (BleScan scan : scans) {
					if (scan != null) {
						if (transmitterSet.contains(scan.getAddress())) {
							continue;
						}
						transmitterSet.add(scan.getAddress());
						// int setSize = transmitterSet.size();
						// Cell newTransmitter = row.createCell(colNum++);
						// newTransmitter.setCellValue(scan.getTime());
						// if (timeMap.containsKey(setSize)) {
						// timeMap.get(setSize).add((int) scan.getTime());
						// } else {
						// List<Integer> times = new ArrayList<>();
						// times.add((int) scan.getTime());
						// timeMap.put(setSize, times);
						// }
						for (int i = (int) scan.getTime(); i <= 10001; i++) {
							Cell num = row.getCell(i);
							num.setCellValue(transmitterSet.size());
						}
					}
				}
			}

			Row emptyRow = sheet.createRow(rowNum++);
			Row msRow = sheet.createRow(rowNum++);
			createMsRow(msRow);

			// Fingerprint fingerprint = devFingerprints.get(0);

			//
			// for (Integer transmitterCount : timeMap.keySet()) {
			// double timeMed =
			// statistics.countMedian(timeMap.get(transmitterCount));
			// Cell timeCell = row.createCell(colNum++);
			// timeCell.setCellValue(timeMed);
			// }
		}

		// Pro kazde zarizeni na danem miste, ktere provedlo alespon pet mereni
		// Pro kazde mereni sepsat casy, kdy se mu podarilo nasnimat unikatni
		// transmittery
		// Median mereni v ramci zarizeni?
	}

	private void createWifiTimeRow(XSSFSheet sheet, List<Fingerprint> fingerprints) {
		Map<String, List<Fingerprint>> fingerprintMap = new HashMap<>();
		for (Fingerprint fingerprint : fingerprints) {
			String device = fingerprint.getDeviceID();
			if (fingerprintMap.containsKey(device)) {
				fingerprintMap.get(device).add(fingerprint);
			} else {
				ArrayList<Fingerprint> devFingerprints = new ArrayList<>();
				devFingerprints.add(fingerprint);
				fingerprintMap.put(device, devFingerprints);
			}
		}
		int rowNum = 0;
		int colNum = 0;
		Row descRow = sheet.createRow(rowNum++);
		Cell name = descRow.createCell(colNum++);
		name.setCellValue("Device");
		createMsRow(descRow);

		for (String device : fingerprintMap.keySet()) {
			List<Fingerprint> devFingerprints = fingerprintMap.get(device);
			if (devFingerprints.size() < 5) {
				continue;
			}
			for (Fingerprint fingerprint : devFingerprints) {
				Row row = sheet.createRow(rowNum++);
				colNum = 0;
				Cell deviceCell = row.createCell(colNum++);
				deviceCell.setCellValue(fingerprint.getBrand() + " " + fingerprint.getModel());

				Set<String> transmitterSet = new HashSet<>();
				List<WifiScan> scans = fingerprint.getWifiScans();
				Collections.sort(scans, new WifiComparator());
				for (int i = colNum; i <= 10001; i++) {
					Cell num = row.createCell(i);
					num.setCellValue(0);
				}
				for (WifiScan scan : scans) {
					if (scan != null) {
						if (transmitterSet.contains(scan.getMAC())) {
							continue;
						}
						transmitterSet.add(scan.getMAC());
						for (int i = (int) scan.getTime(); i <= 10001; i++) {
							Cell num = row.getCell(i);
							num.setCellValue(transmitterSet.size());
						}
					}
				}
			}

			Row emptyRow = sheet.createRow(rowNum++);
			Row msRow = sheet.createRow(rowNum++);
			createMsRow(msRow);
		}
	}

	private void createMsRow(Row row) {
		for (int i = 1; i <= 10001; i++) {
			Cell ms = row.createCell(i);
			ms.setCellValue(i);
		}
	}

	private void createCellScanHeaders(Row row) {
		int colNum = 0;
		Cell device = row.createCell(colNum++);
		device.setCellValue("device");
		Cell rssi = row.createCell(colNum++);
		rssi.setCellValue("RSSI");
		Cell time = row.createCell(colNum++);
		time.setCellValue("time");
	}

	private void createCellScanRow(int rowNum, XSSFSheet sheet, List<CellScan> scans, String deviceName) {
		Row row = sheet.createRow(rowNum);
		int colNum = 0;
		Cell device = row.createCell(colNum++);
		device.setCellValue(deviceName);

		for (CellScan scan : scans) {
			Cell rssi = row.createCell(colNum++);
			rssi.setCellValue(scan.getRssi());
			Cell time = row.createCell(colNum++);
			time.setCellValue(scan.getTime());
		}
	}

	private void createBleHeaders(Row row) {
		int colNum = 0;
		Cell device = row.createCell(colNum++);
		device.setCellValue("device");
		Cell addr = row.createCell(colNum++);
		addr.setCellValue("address");
		Cell rssi = row.createCell(colNum++);
		rssi.setCellValue("RSSI");
		Cell time = row.createCell(colNum++);
		time.setCellValue("time");
	}

	private void createBleRow(int rowNum, XSSFSheet sheet, List<BleScan> scans, String deviceName) {
		Row row = sheet.createRow(rowNum);
		int colNum = 0;
		Cell device = row.createCell(colNum++);
		device.setCellValue(deviceName);
		for (BleScan scan : scans) {
			if (scan != null) {
				Cell addr = row.createCell(colNum++);
				addr.setCellValue(scan.getAddress());
				Cell rssi = row.createCell(colNum++);
				rssi.setCellValue(scan.getRssi());
				Cell time = row.createCell(colNum++);
				time.setCellValue(scan.getTime());
			}
		}
	}

	private void createWifiHeaders(Row row) {
		int colNum = 0;
		Cell device = row.createCell(colNum++);
		device.setCellValue("device");
		Cell channel = row.createCell(colNum++);
		channel.setCellValue("channel");
		Cell mac = row.createCell(colNum++);
		mac.setCellValue("MAC");
		Cell ssid = row.createCell(colNum++);
		ssid.setCellValue("SSID");
		Cell strength = row.createCell(colNum++);
		strength.setCellValue("strength");
		Cell time = row.createCell(colNum++);
		time.setCellValue("time");
	}

	private void createWifiRow(int rowNum, XSSFSheet sheet, List<WifiScan> scans, String deviceName) {
		Row row = sheet.createRow(rowNum);
		int colNum = 0;
		Cell device = row.createCell(colNum++);
		device.setCellValue(deviceName);
		for (WifiScan scan : scans) {
			Cell channel = row.createCell(colNum++);
			channel.setCellValue(scan.getChannel());
			Cell mac = row.createCell(colNum++);
			mac.setCellValue(scan.getMAC());
			Cell ssid = row.createCell(colNum++);
			ssid.setCellValue(scan.getSSID());
			Cell strength = row.createCell(colNum++);
			strength.setCellValue(scan.getStrength());
			Cell time = row.createCell(colNum++);
			time.setCellValue(scan.getTime());
			if (colNum > 16378) {
				break;
			}
		}
	}

	private void createMeanRow(int rowNum, XSSFSheet sheet) {
		Row row = sheet.createRow(rowNum);
		int colNum = 0;
		Cell mean = row.createCell(colNum++);
		mean.setCellValue("Mean");
		Cell cellMean = row.createCell(colNum++);
		cellMean.setCellValue(statistics.getCellMean());
		Cell bleMean = row.createCell(colNum++);
		bleMean.setCellValue(statistics.getBleMean());
		Cell wifiMean = row.createCell(colNum++);
		wifiMean.setCellValue(statistics.getWifiMean());
		Cell cellUMean = row.createCell(colNum++);
		cellUMean.setCellValue(statistics.getCellUMean());
		Cell bleUMean = row.createCell(colNum++);
		bleUMean.setCellValue(statistics.getBleUMean());
		Cell wifiUMean = row.createCell(colNum++);
		wifiUMean.setCellValue(statistics.getWifiUMean());
	}

	private void createMeanWithoutZerosRow(int rowNum, XSSFSheet sheet) {
		Row row = sheet.createRow(rowNum);
		int colNum = 0;
		Cell meanNoZero = row.createCell(colNum++);
		meanNoZero.setCellValue("Mean without zeros");
		Cell cellMean = row.createCell(colNum++);
		cellMean.setCellValue(statistics.getCellMeanNoZeros());
		Cell bleMean = row.createCell(colNum++);
		bleMean.setCellValue(statistics.getBleMeanNoZeros());
		Cell wifiMean = row.createCell(colNum++);
		wifiMean.setCellValue(statistics.getWifiMeanNoZeros());
		Cell cellUMean = row.createCell(colNum++);
		cellUMean.setCellValue(statistics.getCellUMeanNoZeros());
		Cell bleUMean = row.createCell(colNum++);
		bleUMean.setCellValue(statistics.getBleUMeanNoZeros());
		Cell wifiUMean = row.createCell(colNum++);
		wifiUMean.setCellValue(statistics.getWifiUMeanNoZeros());
	}

	private void createModeRow(int rowNum, XSSFSheet sheet) {
		Row row = sheet.createRow(rowNum);
		int colNum = 0;
		Cell mode = row.createCell(colNum++);
		mode.setCellValue("Mode");
		Cell cellMode = row.createCell(colNum++);
		cellMode.setCellValue(statistics.getCellMode());
		Cell bleMode = row.createCell(colNum++);
		bleMode.setCellValue(statistics.getBleMode());
		Cell wifiMode = row.createCell(colNum++);
		wifiMode.setCellValue(statistics.getWifiMode());
		Cell cellUMode = row.createCell(colNum++);
		cellUMode.setCellValue(statistics.getCellUMode());
		Cell bleUMode = row.createCell(colNum++);
		bleUMode.setCellValue(statistics.getBleUMode());
		Cell wifiUMode = row.createCell(colNum++);
		wifiUMode.setCellValue(statistics.getWifiUMode());
	}

	private void createModeWithoutZerosRow(int rowNum, XSSFSheet sheet) {
		Row row = sheet.createRow(rowNum);
		int colNum = 0;
		Cell modeNoZero = row.createCell(colNum++);
		modeNoZero.setCellValue("Mode without zeros");
		Cell cellMode = row.createCell(colNum++);
		cellMode.setCellValue(statistics.getCellModeNoZeros());
		Cell bleMode = row.createCell(colNum++);
		bleMode.setCellValue(statistics.getBleModeNoZeros());
		Cell wifiMode = row.createCell(colNum++);
		wifiMode.setCellValue(statistics.getWifiModeNoZeros());
		Cell cellUMode = row.createCell(colNum++);
		cellUMode.setCellValue(statistics.getCellUModeNoZeros());
		Cell bleUMode = row.createCell(colNum++);
		bleUMode.setCellValue(statistics.getBleUModeNoZeros());
		Cell wifiUMode = row.createCell(colNum++);
		wifiUMode.setCellValue(statistics.getWifiUModeNoZeros());
	}

	private void createMedianRow(int rowNum, XSSFSheet sheet) {
		Row row = sheet.createRow(rowNum);
		int colNum = 0;
		Cell median = row.createCell(colNum++);
		median.setCellValue("Median");
		Cell cellMedian = row.createCell(colNum++);
		cellMedian.setCellValue(statistics.getCellMedian());
		Cell bleMedian = row.createCell(colNum++);
		bleMedian.setCellValue(statistics.getBleMedian());
		Cell wifiMedian = row.createCell(colNum++);
		wifiMedian.setCellValue(statistics.getWifiMedian());
		Cell cellUMedian = row.createCell(colNum++);
		cellUMedian.setCellValue(statistics.getCellUMedian());
		Cell bleUMedian = row.createCell(colNum++);
		bleUMedian.setCellValue(statistics.getBleUMedian());
		Cell wifiUMedian = row.createCell(colNum++);
		wifiUMedian.setCellValue(statistics.getWifiUMedian());
	}

	private void createMedianWithoutZerosRow(int rowNum, XSSFSheet sheet) {
		Row row = sheet.createRow(rowNum);
		int colNum = 0;
		Cell medianNoZero = row.createCell(colNum++);
		medianNoZero.setCellValue("Median without zeros");
		Cell cellMedian = row.createCell(colNum++);
		cellMedian.setCellValue(statistics.getCellMedianNoZeros());
		Cell bleMedian = row.createCell(colNum++);
		bleMedian.setCellValue(statistics.getBleMedianNoZeros());
		Cell wifiMedian = row.createCell(colNum++);
		wifiMedian.setCellValue(statistics.getWifiMedianNoZeros());
		Cell cellUMedian = row.createCell(colNum++);
		cellUMedian.setCellValue(statistics.getCellUMedianNoZeros());
		Cell bleUMedian = row.createCell(colNum++);
		bleUMedian.setCellValue(statistics.getBleUMedianNoZeros());
		Cell wifiUMedian = row.createCell(colNum++);
		wifiUMedian.setCellValue(statistics.getWifiUMedianNoZeros());
	}

}
