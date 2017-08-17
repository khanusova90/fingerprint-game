/**
 * 
 */
package cz.hanusova.fingerprintgame.service;

import cz.hanusova.fingerprintgame.fingerprint.Fingerprint;

/**
 * @author khanusova
 *
 */
public interface FingerprintService {

	/**
	 * Saves new fingerprint to separate file and couchbase DB
	 * 
	 * @param fingerprint
	 *            {@link Fingerprint} sent from client
	 */
	void saveFingerprint(Fingerprint fingerprint);

}
