/**
 * 
 */
package cz.hanusova.fingerprintgame.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.hanusova.fingerprintgame.model.ActivityEnum;
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
	@Transactional
	public void startNewActivity(Place place, Float amount, AppUser user) {
		UserActivity activity = new UserActivity(place, amount);
		userActivityRepository.save(activity);

		user.getActivities().add(activity);
		updateInventory(place, user, amount);

		userRepository.save(user);
	}

	private void updateInventory(Place place, AppUser user, Float amount) {
		ActivityEnum placeActivity = place.getPlaceType().getActivity();
		switch (placeActivity) {
		case MINE:
			inventoryService.updateWorkerAmount(amount, user);
			break;
		case BUILD:
			inventoryService.updateStoneAmount(amount * 10, user);
			inventoryService.updateWoodAmount(amount * 10, user);
			inventoryService.updateWorkerAmount(amount * -1, user);
			break;
		default:
			break;
		}

	}

	@Override
	@Transactional
	public void removeActivity(UserActivity activity, AppUser user) {
		inventoryService.updateWorkerAmount(activity.getMaterialAmount() * -1, user);
		user.getActivities().remove(activity);
		userRepository.save(user);

		userActivityRepository.delete(activity);
	}

	@Override
	@Transactional
	public void changeActivity(UserActivity activity, Float workersAmount, AppUser user) {
		// TODO: vyresit pro zmenu stavby
		inventoryService.updateWorkerAmount(activity.getMaterialAmount() * -1, user);
		activity.setMaterialAmount(workersAmount);
		activity.setStartTime(new Date());
		inventoryService.updateWorkerAmount(workersAmount, user);
	}

}
