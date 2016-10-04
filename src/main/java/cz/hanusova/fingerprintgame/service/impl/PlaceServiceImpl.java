package cz.hanusova.fingerprintgame.service.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.hanusova.fingerprintgame.model.Activity;
import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.UserActivity;
import cz.hanusova.fingerprintgame.repository.PlaceRepository;
import cz.hanusova.fingerprintgame.repository.UserActivityRepository;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService {
	private static final Log logger = LogFactory.getLog(PlaceServiceImpl.class);

	@Autowired
	private PlaceRepository placeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserActivityRepository userActivityRepository;

	@Override
	@Transactional(readOnly = true)
	public Place getPlaceByCode(String code) {
		return placeRepository.findFirstByCodeFetch(code);
	}

	@Override
	@Transactional
	public void startActivity(String username, Place place, Activity activity) {
		logger.info("Adding new activity ( " + activity.getName() + ") to user " + username + " at " + place.getName());

		AppUser user = userRepository.findByUsername(username);
		UserActivity userActivity = new UserActivity();
		userActivity.setActivity(activity);
		// userActivity.setAppUser(user);
		userActivity.setStartTime(new Date());
		// TODO: pokud je potreba nejaky material, tak ho pridat
		user.getActivities().add(userActivity);
		userRepository.save(user);
		userActivityRepository.save(userActivity);
	}

}
