package cz.hanusova.fingerprintgame.service.impl;

import java.util.Date;
import java.util.Set;

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
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.ActivityService;
import cz.hanusova.fingerprintgame.service.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService {
	private static final Log logger = LogFactory.getLog(PlaceServiceImpl.class);

	private PlaceRepository placeRepository;
	private UserRepository userRepository;
	private ActivityService activityService;

	@Autowired
	public PlaceServiceImpl(PlaceRepository placeRepository, UserRepository userRepository,
			ActivityService activityService) {
		this.placeRepository = placeRepository;
		this.userRepository = userRepository;
		this.activityService = activityService;
	}

	@Override
	@Transactional(readOnly = true)
	public Place getPlaceByCode(String code) {
		return placeRepository.findFirstByCode(code);
	}

	@Override
	@Transactional
	@Deprecated
	public void startActivity(String username, Place place, Activity activity) {
		logger.info("Adding new activity ( " + activity.getName() + ") to user " + username + " at " + place.getName());

		AppUser user = userRepository.findByUsername(username);
		UserActivity userActivity = new UserActivity();
		userActivity.setStartTime(new Date());
		user.getActivities().add(userActivity);
		userRepository.save(user);
	}

	public Set<UserActivity> startActivity(AppUser user, Place place, Float workerAmount) {
		Set<UserActivity> activities = user.getActivities();
		UserActivity existingActivity = activities.stream().filter(a -> a.getPlace().equals(place)).findAny()
				.orElse(null);

		if (existingActivity == null && workerAmount != 0) {
			activityService.startNewActivity(place, workerAmount, user);
		} else if (workerAmount == 0) {
			activityService.removeActivity(existingActivity, user);
		} else if (!workerAmount.equals(existingActivity.getMaterialAmount())) {
			activityService.changeActivity(existingActivity, workerAmount, user);
		}

		return user.getActivities();
	}

}
