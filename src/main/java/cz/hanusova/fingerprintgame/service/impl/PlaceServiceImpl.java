package cz.hanusova.fingerprintgame.service.impl;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.UserActivity;
import cz.hanusova.fingerprintgame.repository.PlaceRepository;
import cz.hanusova.fingerprintgame.service.ActivityService;
import cz.hanusova.fingerprintgame.service.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService {
	private static final Log logger = LogFactory.getLog(PlaceServiceImpl.class);

	private PlaceRepository placeRepository;
	private ActivityService activityService;

	@Autowired
	public PlaceServiceImpl(PlaceRepository placeRepository, ActivityService activityService) {
		this.placeRepository = placeRepository;
		this.activityService = activityService;
	}

	@Override
	@Transactional(readOnly = true)
	public Place getPlaceByCode(String code) {
		return placeRepository.findFirstByCode(code);
	}

	@Override
	@Transactional
	public Set<UserActivity> startActivity(AppUser user, Place place, Float workerAmount) {
		logger.info("Starting activity for user " + user.getUsername() + " at place ID " + place.getIdPlace());
		Set<UserActivity> activities = user.getActivities();
		UserActivity existingActivity = null;
		if (activities != null && !activities.isEmpty()) {
			existingActivity = activities.stream().filter(a -> a.getPlace().equals(place)).findAny().orElse(null);
		}
		// TODO: rozdelit podle typu aktivity
		// TODO: prepocitani inventare az odsud
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
