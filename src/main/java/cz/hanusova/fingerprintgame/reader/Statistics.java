package cz.hanusova.fingerprintgame.reader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cz.hanusova.fingerprintgame.fingerprint.BleScan;
import cz.hanusova.fingerprintgame.fingerprint.CellScan;
import cz.hanusova.fingerprintgame.fingerprint.Fingerprint;
import cz.hanusova.fingerprintgame.fingerprint.WifiScan;

public class Statistics {

	private List<Fingerprint> fingerprints;
	private List<Integer> uniqueCells;
	private List<Integer> uniqueBles;
	private List<Integer> uniqueWifis;

	private double cellMean;
	private double cellUMean;
	private double cellMeanNoZeros;
	private double cellUMeanNoZeros;

	private double wifiMean;
	private double wifiUMean;
	private double wifiMeanNoZeros;
	private double wifiUMeanNoZeros;

	private double bleMean;
	private double bleUMean;
	private double bleMeanNoZeros;
	private double bleUMeanNoZeros;

	private double cellMode;
	private double cellUMode;
	private double cellModeNoZeros;
	private double cellUModeNoZeros;

	private double wifiMode;
	private double wifiUMode;
	private double wifiModeNoZeros;
	private double wifiUModeNoZeros;

	private double bleMode;
	private double bleUMode;
	private double bleModeNoZeros;
	private double bleUModeNoZeros;

	private double cellMedian;
	private double cellUMedian;
	private double cellMedianNoZeros;
	private double cellUMedianNoZeros;

	private double wifiMedian;
	private double wifiUMedian;
	private double wifiMedianNoZeros;
	private double wifiUMedianNoZeros;

	private double bleMedian;
	private double bleUMedian;
	private double bleMedianNoZeros;
	private double bleUMedianNoZeros;

	public Statistics(List<Fingerprint> fingerprints) {
		this.fingerprints = fingerprints;
		uniqueBles = new ArrayList<>();
		uniqueCells = new ArrayList<>();
		uniqueWifis = new ArrayList<>();
	}

	public int getUniqueCellCount(List<CellScan> scans) {
		List<String> ids = new ArrayList<>();
		for (CellScan scan : scans) {
			String id = String.valueOf(scan.getRssi());
			if (!ids.contains(id)) {
				ids.add(id);
			}
		}
		uniqueCells.add(ids.size());
		return ids.size();
	}

	public int getUniqueBleCount(List<BleScan> scans) {
		List<String> ids = new ArrayList<>();
		for (BleScan scan : scans) {
			if (scan != null) {
				if (!ids.contains(scan.getAddress())) {
					ids.add(scan.getAddress());
				}
			}
		}
		uniqueBles.add(ids.size());
		return ids.size();
	}

	public int getUniqueWifiCount(List<WifiScan> scans) {
		List<String> ids = new ArrayList<>();
		for (WifiScan scan : scans) {
			if (!ids.contains(scan.getMAC())) {
				ids.add(scan.getMAC());
			}
		}
		uniqueWifis.add(ids.size());
		return ids.size();
	}

	public void countStatistics() {
		countCellStats();
		countUniqueCellStats();
		countWifiStats();
		countUniqueWifiStats();
		countBleStats();
		countUniqueBleStats();
	}

	private void countCellStats() {
		List<Integer> cellCounts = new ArrayList<>();
		List<Integer> cellZeroCounts = new ArrayList<>();
		for (Fingerprint f : fingerprints) {
			int count = f.getCellScans().size();
			cellZeroCounts.add(count);
			if (count != 0) {
				cellCounts.add(f.getCellScans().size());
			}
		}
		Collections.sort(cellCounts);
		Collections.sort(cellZeroCounts);
		cellMean = countMean(cellZeroCounts);
		cellMeanNoZeros = countMean(cellCounts);
		cellMode = countMode(cellZeroCounts);
		cellModeNoZeros = countMean(cellCounts);
		cellMedian = countMedian(cellZeroCounts);
		cellMedianNoZeros = countMedian(cellCounts);
	}

	private void countUniqueCellStats() {
		List<Integer> cellsNoZero = new ArrayList<>();
		for (Integer i : uniqueCells) {
			if (i != 0) {
				cellsNoZero.add(i);
			}
		}
		Collections.sort(uniqueCells);
		Collections.sort(cellsNoZero);
		cellUMean = countMean(uniqueCells);
		cellUMeanNoZeros = countMean(cellsNoZero);
		cellUMode = countMode(uniqueCells);
		cellUModeNoZeros = countMode(cellsNoZero);
		cellUMedian = countMedian(uniqueCells);
		cellUMedianNoZeros = countMedian(cellsNoZero);
	}

	private void countWifiStats() {
		List<Integer> wifiCounts = new ArrayList<>();
		List<Integer> wifiNoZero = new ArrayList<>();
		for (Fingerprint f : fingerprints) {
			int count = f.getWifiScans().size();
			wifiCounts.add(count);
			if (count != 0) {
				wifiNoZero.add(count);
			}
		}
		Collections.sort(wifiCounts);
		Collections.sort(wifiNoZero);
		wifiMean = countMean(wifiCounts);
		wifiMeanNoZeros = countMean(wifiNoZero);
		wifiMode = countMode(wifiCounts);
		wifiModeNoZeros = countMode(wifiNoZero);
		wifiMedian = countMedian(wifiCounts);
		wifiMedianNoZeros = countMedian(wifiNoZero);
	}

	private void countUniqueWifiStats() {
		List<Integer> wifiNoZero = new ArrayList<>();
		for (Integer i : uniqueWifis) {
			if (i != 0) {
				wifiNoZero.add(i);
			}
		}
		Collections.sort(uniqueWifis);
		Collections.sort(wifiNoZero);
		wifiUMean = countMean(uniqueWifis);
		wifiUMeanNoZeros = countMean(wifiNoZero);
		wifiUMode = countMode(uniqueWifis);
		wifiUModeNoZeros = countMode(wifiNoZero);
		wifiUMedian = countMedian(uniqueWifis);
		wifiUMedianNoZeros = countMedian(wifiNoZero);
	}

	private void countBleStats() {
		List<Integer> bleCounts = new ArrayList<>();
		List<Integer> bleNoZero = new ArrayList<>();
		for (Fingerprint f : fingerprints) {
			int count = f.getBleScans().size();
			bleCounts.add(count);
			if (count != 0) {
				bleNoZero.add(count);
			}
		}
		Collections.sort(bleCounts);
		Collections.sort(bleNoZero);
		bleMean = countMean(bleCounts);
		bleMeanNoZeros = countMean(bleNoZero);
		bleMode = countMode(bleCounts);
		bleModeNoZeros = countMode(bleNoZero);
		bleMedian = countMedian(bleCounts);
		bleMedianNoZeros = countMedian(bleNoZero);
	}

	private void countUniqueBleStats() {
		List<Integer> bleNoZero = new ArrayList<>();
		for (Integer i : uniqueBles) {
			if (i != 0) {
				bleNoZero.add(i);
			}
		}
		Collections.sort(uniqueBles);
		Collections.sort(bleNoZero);
		bleUMean = countMean(uniqueBles);
		bleUMeanNoZeros = countMean(bleNoZero);
		bleUMode = countMode(uniqueBles);
		bleUModeNoZeros = countMode(bleNoZero);
		bleUMedian = countMedian(uniqueBles);
		bleUMedianNoZeros = countMedian(bleNoZero);
	}

	public double countMean(List<Integer> list) {
		int sum = 0;
		for (Integer num : list) {
			sum += num;
		}
		return sum / (double) list.size();
	}

	public double countMedian(List<Integer> list) {
		int middle = list.size() / 2;
		if (list.isEmpty()) {
			return 0;
		}
		if (list.size() % 2 == 1 || list.size() < 2) {
			return list.get(middle);
		} else {
			return (list.get(middle - 1) + list.get(middle)) / 2.0;
		}
	}

	public int countMode(List<Integer> list) {
		int maxValue = 0;
		int maxCount = 0;

		for (Integer num : list) {
			int count = 0;
			for (Integer n : list) {
				if (num.equals(n)) {
					count++;
				}
			}
			if (count > maxCount) {
				maxCount = count;
				maxValue = num;
			}
		}

		return maxValue;
	}

	public double getCellMean() {
		return cellMean;
	}

	public double getCellUMean() {
		return cellUMean;
	}

	public double getCellMeanNoZeros() {
		return cellMeanNoZeros;
	}

	public double getCellUMeanNoZeros() {
		return cellUMeanNoZeros;
	}

	public double getWifiMean() {
		return wifiMean;
	}

	public double getWifiUMean() {
		return wifiUMean;
	}

	public double getWifiMeanNoZeros() {
		return wifiMeanNoZeros;
	}

	public double getWifiUMeanNoZeros() {
		return wifiUMeanNoZeros;
	}

	public double getBleMean() {
		return bleMean;
	}

	public double getBleUMean() {
		return bleUMean;
	}

	public double getBleMeanNoZeros() {
		return bleMeanNoZeros;
	}

	public double getBleUMeanNoZeros() {
		return bleUMeanNoZeros;
	}

	public double getCellMode() {
		return cellMode;
	}

	public double getCellUMode() {
		return cellUMode;
	}

	public double getCellModeNoZeros() {
		return cellModeNoZeros;
	}

	public double getCellUModeNoZeros() {
		return cellUModeNoZeros;
	}

	public double getWifiMode() {
		return wifiMode;
	}

	public double getWifiUMode() {
		return wifiUMode;
	}

	public double getWifiModeNoZeros() {
		return wifiModeNoZeros;
	}

	public double getWifiUModeNoZeros() {
		return wifiUModeNoZeros;
	}

	public double getBleMode() {
		return bleMode;
	}

	public double getBleUMode() {
		return bleUMode;
	}

	public double getBleModeNoZeros() {
		return bleModeNoZeros;
	}

	public double getBleUModeNoZeros() {
		return bleUModeNoZeros;
	}

	public double getCellMedian() {
		return cellMedian;
	}

	public double getCellUMedian() {
		return cellUMedian;
	}

	public double getCellMedianNoZeros() {
		return cellMedianNoZeros;
	}

	public double getCellUMedianNoZeros() {
		return cellUMedianNoZeros;
	}

	public double getWifiMedian() {
		return wifiMedian;
	}

	public double getWifiUMedian() {
		return wifiUMedian;
	}

	public double getWifiMedianNoZeros() {
		return wifiMedianNoZeros;
	}

	public double getWifiUMedianNoZeros() {
		return wifiUMedianNoZeros;
	}

	public double getBleMedian() {
		return bleMedian;
	}

	public double getBleUMedian() {
		return bleUMedian;
	}

	public double getBleMedianNoZeros() {
		return bleMedianNoZeros;
	}

	public double getBleUMedianNoZeros() {
		return bleUMedianNoZeros;
	}

}
