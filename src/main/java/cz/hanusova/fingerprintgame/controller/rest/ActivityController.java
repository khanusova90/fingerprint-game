/**
 * 
 */
package cz.hanusova.fingerprintgame.controller.rest;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.hanusova.fingerprintgame.model.ActivityEnum;
import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Material;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.UserActivity;
import cz.hanusova.fingerprintgame.repository.MaterialRepository;
import cz.hanusova.fingerprintgame.repository.UserActivityRepository;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.utils.UserUtils;

/**
 * @author khanusova
 * 
 *         Controller for handling activities from mobile application
 *
 */
@RestController
@RequestMapping("/android/1.0/activity")
public class ActivityController {
	private static final Log logger = LogFactory.getLog(ActivityController.class);

	@Autowired
	private MaterialRepository materialRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserActivityRepository userActivityRepository;
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailService;

	@RequestMapping("/start")
	public void startActivity(@RequestParam Integer materialAmount, @RequestBody Place place) {
		UserActivity userActivity;
		ActivityEnum activityType = place.getPlaceType().getActivity();
		String username = UserUtils.getActualUsername();
		AppUser user = userRepository.findByUsername(username);
		// TODO: vyclenit do service, nemelo by se resit v controlleru
		// TODO: zjistit, jestli uz uzivatel na tom miste neco tezi
		if (materialAmount == 0) {
			// TODO: najit aktivitu podle mista a ukoncit
		} else {
			logger.info("Starting new activity for user " + username + ". Place code: " + place.getCode());
			userActivity = new UserActivity();
			userActivity.setStartTime(new Date());
			userActivity.setActivity(activityType);

			switch (activityType) {
			case MINE:
				Material worker = materialRepository.findWorker();
				userActivity.setMaterial(worker);
				userActivity.setMaterialAmount(Float.valueOf(materialAmount)); // TODO:
																				// dle
																				// mista
																				// vyresit,
																				// jakou
																				// surovinu
																				// uzivatel
																				// tezi
																				// a
																				// pricitat
																				// mu
																				// ji
				// TODO: odecist pracovniky z inventare
				break;
			default: // TODO: neznama aktivita
				break;
			}

			userActivityRepository.save(userActivity);
			user.getActivities().add(userActivity);
			userRepository.save(user);
		}
	}

}
