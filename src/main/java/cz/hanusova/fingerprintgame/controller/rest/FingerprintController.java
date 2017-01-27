/**
 * 
 */
package cz.hanusova.fingerprintgame.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.hanusova.fingerprintgame.fingerprint.Fingerprint;
import cz.hanusova.fingerprintgame.service.FingerprintService;

/**
 * Controller for collecting fingerprints from client
 * 
 * @author khanusova
 *
 */
@RestController
@RequestMapping("/android/1.0/fingerprint")
public class FingerprintController {

	@Autowired
	private FingerprintService fingerprintService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void saveFingerprint(@RequestBody Fingerprint fingerprint) {
		fingerprintService.saveFingerprint(fingerprint);
	}

}
