/**
 * 
 */
package cz.hanusova.fingerprintgame.controller.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.repository.MaterialRepository;
import cz.hanusova.fingerprintgame.repository.UserActivityRepository;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.PlaceService;
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
	@Autowired
	private PlaceService placeService;

	@RequestMapping("/start")
	public void startActivity(@RequestParam Integer materialAmount, @RequestBody Place place) {
		String username = UserUtils.getActualUsername();
		AppUser user = userRepository.findByUsername(username);
		placeService.startActivity(user, place, Float.valueOf(materialAmount));
	}

}
