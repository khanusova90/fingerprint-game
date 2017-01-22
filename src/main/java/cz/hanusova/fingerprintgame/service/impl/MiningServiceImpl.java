/**
 * 
 */
package cz.hanusova.fingerprintgame.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.hanusova.fingerprintgame.model.ActivityEnum;
import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Inventory;
import cz.hanusova.fingerprintgame.model.Material;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.UserActivity;
import cz.hanusova.fingerprintgame.repository.InventoryRepository;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.InventoryService;
import cz.hanusova.fingerprintgame.service.MiningService;

/**
 * @author khanusova
 *
 */
@Service
public class MiningServiceImpl implements MiningService {
	// FIXME: rename class
	private Log logger = LogFactory.getLog(MiningServiceImpl.class);

	private UserRepository userRepository;
	private InventoryRepository inventoryRepository; // TODO: je potreba? ->
														// podivat se na kaskady
	private InventoryService inventoryService;

	@Autowired
	public MiningServiceImpl(UserRepository userRepository, InventoryRepository inventoryRepository,
			InventoryService inventoryService) {
		this.userRepository = userRepository;
		this.inventoryRepository = inventoryRepository;
		this.inventoryService = inventoryService;
	}

	@Scheduled(fixedRate = 60_000)
	@Transactional
	@Override
	public void startMining() {
		logger.info("Start mining");
		List<AppUser> users = userRepository.findAll();
		for (AppUser user : users) {
			List<UserActivity> activities = user.getActivities();
			for (UserActivity activity : activities) {
				Place place = activity.getPlace();
				ActivityEnum activityType = place.getPlaceType().getActivity();
				switch (activityType) {
				case MINE:
					float workers = activity.getMaterialAmount();
					Material material = place.getMaterial();
					for (Inventory inventory : user.getInventory()) {
						if (inventory.getMaterial().equals(material)) {
							logger.info(
									"Adding " + workers + " of " + material.getName() + " to " + user.getUsername());
							BigDecimal actualAmount = inventory.getAmount();
							inventory.setAmount(actualAmount.add(new BigDecimal(workers)));
							inventoryRepository.save(inventory);
							break;
						}
					}
					break;
				case BUILD:
					float housesAmount = activity.getMaterialAmount();
					logger.info("User " + user.getUsername() + " is paying rent for " + housesAmount + " houses");
					;
					inventoryService.updateGoldAmount(0.5f * housesAmount, user);
					inventoryService.updateFoodAmount(0.5f * housesAmount, user);
					break;
				default:
					break;
				}
			}
			userRepository.save(user);
		}

	}

}
