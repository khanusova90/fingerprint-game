package cz.hanusova.fingerprintgame.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.UserActivity;
import cz.hanusova.fingerprintgame.repository.PlaceRepository;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.ActivityService;
import cz.hanusova.fingerprintgame.service.PlaceService;
import cz.hanusova.fingerprintgame.utils.UserUtils;

@Service
public class PlaceServiceImpl implements PlaceService {
	private static final Log logger = LogFactory.getLog(PlaceServiceImpl.class);

	private PlaceRepository placeRepository;
	private ActivityService activityService;
	private UserRepository userRepository;

	@Autowired
	public PlaceServiceImpl(PlaceRepository placeRepository, ActivityService activityService,
			UserRepository userRepository) {
		this.placeRepository = placeRepository;
		this.activityService = activityService;
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public Place getPlaceByCode(String code) {
		return placeRepository.findFirstByCode(code);
	}

	@Override
	@Transactional
	public void checkUserPlace(Place place) {
		String username = UserUtils.getActualUsername();
		AppUser user = userRepository.findByUsername(username);
		List<Place> userPlaces = user.getPlaces();
		Place userPlace = userPlaces.stream().filter(p -> p.equals(place)).findAny().orElse(null);
		if (userPlace == null) {
			logger.info("Adding place ID " + place.getIdPlace() + " to user " + username);
			userPlaces.add(place);
			userRepository.save(user);
		}
	}

	@Override
	@Transactional
	public AppUser startActivity(AppUser user, Place place, Float workerAmount) {
		logger.info("Starting activity for user " + user.getUsername() + " at place ID " + place.getIdPlace() + " with "
				+ workerAmount + " workers");

		List<UserActivity> activities = user.getActivities();
		UserActivity existingActivity = null;
		if (activities != null && !activities.isEmpty()) {
			existingActivity = activities.stream().filter(a -> a.getPlace().equals(place)).findAny().orElse(null);
		}
		if (existingActivity == null && workerAmount != 0) {
			activityService.startNewActivity(place, workerAmount, user);
		} else if (workerAmount == 0) {
			activityService.removeActivity(existingActivity, user);
		} else if (!workerAmount.equals(existingActivity.getMaterialAmount())) {
			activityService.changeActivity(existingActivity, workerAmount, user);
		}

		return user;
	}

}
