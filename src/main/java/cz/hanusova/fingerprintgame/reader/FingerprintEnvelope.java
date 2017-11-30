package cz.hanusova.fingerprintgame.reader;

import cz.hanusova.fingerprintgame.fingerprint.Fingerprint;

public class FingerprintEnvelope {

	private Fingerprint beacon;

	public Fingerprint getBeacon() {
		return beacon;
	}

	public void setBeacon(Fingerprint beacon) {
		this.beacon = beacon;
	}

	// private List<Fingerprint> fingerprints;
	//
	// public List<Fingerprint> getFingerprints() {
	// return fingerprints;
	// }
	//
	// public void setFingerprints(List<Fingerprint> fingerprints) {
	// this.fingerprints = fingerprints;
	// }

}
