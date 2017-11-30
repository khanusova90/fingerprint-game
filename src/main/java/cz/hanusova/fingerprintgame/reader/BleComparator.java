package cz.hanusova.fingerprintgame.reader;

import java.util.Comparator;

import cz.hanusova.fingerprintgame.fingerprint.BleScan;

public class BleComparator implements Comparator<BleScan> {

	@Override
	public int compare(BleScan o1, BleScan o2) {
		if (o1 == null) {
			return -1;
		}
		if (o2 == null) {
			return 1;
		}
		return (int) (o1.getTime() - o2.getTime());
	}

}
