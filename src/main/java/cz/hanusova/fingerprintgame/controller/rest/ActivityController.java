/**
 * 
 */
package cz.hanusova.fingerprintgame.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Item;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.ItemService;
import cz.hanusova.fingerprintgame.service.PlaceService;
import cz.hanusova.fingerprintgame.service.UserService;
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

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ItemService itemService;
	@Autowired
	private PlaceService placeService;
	@Autowired
	private UserService userService;

	@RequestMapping("/start")
	public AppUser startActivity(@RequestParam Integer materialAmount, @RequestBody Place place) {
		String username = UserUtils.getActualUsername();
		AppUser user = userRepository.findByUsername(username);
		return placeService.startActivity(user, place, Float.valueOf(materialAmount));
	}

	@RequestMapping("/buy")
	public AppUser buyItem(@RequestBody Item item) {
		AppUser user = userService.getActualUser();
		return itemService.addItem(user, item);
	}

}
