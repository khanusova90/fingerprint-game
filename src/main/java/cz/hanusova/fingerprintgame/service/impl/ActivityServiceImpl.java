/**
 * 
 */
package cz.hanusova.fingerprintgame.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.UserActivity;
import cz.hanusova.fingerprintgame.repository.UserActivityRepository;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.ActivityService;
import cz.hanusova.fingerprintgame.service.InventoryService;

/**
 * @author khanusova
 *
 */
@Service
public class ActivityServiceImpl implements ActivityService {

	private UserActivityRepository userActivityRepository;
	private UserRepository userRepository;
	private InventoryService inventoryService;

	@Autowired
	public ActivityServiceImpl(UserActivityRepository userActivityRepository, UserRepository userRepository,
			InventoryService inventoryService) {
		this.userActivityRepository = userActivityRepository;
		this.userRepository = userRepository;
		this.inventoryService = inventoryService;
	}

	@Override
	public void startNewActivity(Place place, Float workerAmount, AppUser user) {
		UserActivity activity = new UserActivity(place, workerAmount);
		userActivityRepository.save(activity);

		user.getActivities().add(activity);
		inventoryService.updateWorkerAmount(workerAmount, user);
		userRepository.save(user);
	}

	@Override
	public void removeActivity(UserActivity activity, AppUser user) {
		inventoryService.updateWorkerAmount(activity.getMaterialAmount() * -1, user);
		user.getActivities().remove(activity);
		userRepository.save(user);

		userActivityRepository.delete(activity);
	}

	@Override
	public void changeActivity(UserActivity activity, Float workersAmount, AppUser user) {
		inventoryService.updateWorkerAmount(activity.getMaterialAmount() * -1, user);
		activity.setMaterialAmount(workersAmount);
		activity.setStartTime(new Date());
		inventoryService.updateWorkerAmount(workersAmount, user);
	}

}
