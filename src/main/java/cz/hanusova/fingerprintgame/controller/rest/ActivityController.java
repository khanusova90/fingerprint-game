/**
 * 
 */
package cz.hanusova.fingerprintgame.controller.rest;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.UserActivity;
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
	private UserRepository userRepository;
	@Autowired
	private PlaceService placeService;

	@RequestMapping("/start")
	public List<UserActivity> startActivity(@RequestParam Integer materialAmount, @RequestBody Place place) {
		String username = UserUtils.getActualUsername();
		AppUser user = userRepository.findByUsername(username);
		return placeService.startActivity(user, place, Float.valueOf(materialAmount));
	}

}
