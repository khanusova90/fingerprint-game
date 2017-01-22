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
import cz.hanusova.fingerprintgame.service.MiningService;

/**
 * @author khanusova
 *
 */
@Service
public class MiningServiceImpl implements MiningService {
	private Log logger = LogFactory.getLog(MiningServiceImpl.class);

	private UserRepository userRepository;
	private InventoryRepository inventoryRepository; // TODO: je potreba? ->
														// podivat se na kaskady

	/**
	 * 
	 */
	@Autowired
	public MiningServiceImpl(UserRepository userRepository, InventoryRepository inventoryRepository) {
		this.userRepository = userRepository;
		this.inventoryRepository = inventoryRepository;
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
				if (ActivityEnum.MINE.equals(activityType)) {
					float workers = activity.getMaterialAmount();
					Material material = place.getMaterial();
					for (Inventory inventory : user.getInventory()) {
						if (inventory.getMaterial().equals(material)) {
							logger.info(
									"Adding " + workers + " of " + material.getName() + " to " + user.getUsername());
							BigDecimal actualAmount = inventory.getAmount();
							// TODO: upravit pocet
							inventory.setAmount(actualAmount.add(new BigDecimal(workers)));
							inventoryRepository.save(inventory);
							break;
						}
					}
				}
			}
			userRepository.save(user);
		}

	}

}
