/**
 * 
 */
package cz.hanusova.fingerprintgame.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.hanusova.fingerprintgame.model.ActivityEnum;

/**
 * @author khanusova
 * 
 *         Controller for handling activities from mobile application
 *
 */
@RestController
@RequestMapping("/android/1.0/activity")
public class ActivityController {

	@RequestMapping("/start")
	public void startActivity(@RequestParam ActivityEnum activityType, @RequestParam Integer materialAmount) {
		switch (activityType) {
		case MINE:

			break;
		default: // TODO: neznama aktivita
			break;
		}

	}

}
