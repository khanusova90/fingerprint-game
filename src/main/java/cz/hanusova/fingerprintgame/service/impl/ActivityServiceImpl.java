/**
 * 
 */
package cz.hanusova.fingerprintgame.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
	private static final Log logger = LogFactory.getLog(ActivityServiceImpl.class);

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
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void startNewActivity(Place place, Float amount, AppUser user) {
		user = userRepository.findOne(user.getIdUser()); // Refresh user for
															// this transaction
		UserActivity activity = new UserActivity(place, amount);
		userActivityRepository.save(activity);

		user.getActivities().add(activity);
		int xp = user.getCharacter().getXp();
		user.getCharacter().setXp(xp + 1);
		updateInventory(place, user, amount);

		userRepository.save(user);
	}

	/**
	 * Updates user inventory (different for each {@link ActivityEnum} that
	 * takes place in place param
	 * 
	 * @param place
	 * @param user
	 * @param amount
	 */
	private void updateInventory(Place place, AppUser user, Float amount) {
		ActivityEnum placeActivity = place.getPlaceType().getActivity();
		switch (placeActivity) {
		case MINE:
			inventoryService.updateWorkerAmount(amount, user);
			inventoryService.updateMaterial(place.getMaterial(), user, amount);
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
		ActivityEnum placeActivity = activity.getPlace().getPlaceType().getActivity();
		switch (placeActivity) {
		case MINE:
			inventoryService.updateWorkerAmount(activity.getMaterialAmount() * -1, user);
			user.getActivities().remove(activity);
			userActivityRepository.delete(activity);
			break;
		case BUILD:
			inventoryService.stopBuilding(activity, user);
			break;
		default:
			break;
		}
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void changeActivity(UserActivity activity, Float workersAmount, AppUser user) {
		ActivityEnum placeActivity = activity.getPlace().getPlaceType().getActivity();
		switch (placeActivity) {
		case MINE:
			inventoryService.updateWorkerAmount(activity.getMaterialAmount() * -1, user);
			activity.setMaterialAmount(workersAmount);
			activity.setStartTime(new Date());
			inventoryService.updateWorkerAmount(workersAmount, user);
			break;
		case BUILD:
			inventoryService.stopBuilding(activity, user);
			startNewActivity(activity.getPlace(), workersAmount, user);
			break;
		default:
			break;
		}

	}

	@Scheduled(fixedRate = 60_000)
	@Override
	@Transactional
	public void checkRunningActivities() {
		logger.info("Checking activities");
		List<AppUser> users = userRepository.findAll();
		for (AppUser user : users) {
			for (UserActivity activity : user.getActivities()) {
				Place place = activity.getPlace();
				ActivityEnum activityType = place.getPlaceType().getActivity();
				switch (activityType) {
				case MINE:
					float workers = activity.getMaterialAmount();
					if (inventoryService.hasEnoughFood(workers, user)) {
						inventoryService.mine(place, user, workers);
					} else {
						inventoryService.updateWorkerAmount(workers * -1, user);
						userActivityRepository.delete(activity);
						logger.info("User " + user.getUsername()
								+ " does not have enough food to feed workers. Stopping activity at place ID "
								+ place.getIdPlace());
					}
					break;
				case BUILD:
					if (inventoryService.hasEnoughGold(activity.getMaterialAmount(), user)) {
						inventoryService.payRent(activity, user);
					} else {
						logger.info("User " + user.getUsername()
								+ " does not have enough gold to pay workers living at place ID " + place.getIdPlace()
								+ ". ");
						inventoryService.stopBuilding(activity, user);
					}
					break;
				default:
					break;
				}
			}
		}

	}

}
