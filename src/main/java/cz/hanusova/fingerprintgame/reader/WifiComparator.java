package cz.hanusova.fingerprintgame.reader;

import java.util.Comparator;

import cz.hanusova.fingerprintgame.fingerprint.WifiScan;

public class WifiComparator implements Comparator<WifiScan> {

	@Override
	public int compare(WifiScan o1, WifiScan o2) {
		return (int) (o1.getTime() - o2.getTime());
	}

}
