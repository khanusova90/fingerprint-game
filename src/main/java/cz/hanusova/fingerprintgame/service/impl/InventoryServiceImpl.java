/**
 * 
 */
package cz.hanusova.fingerprintgame.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Inventory;
import cz.hanusova.fingerprintgame.model.Material;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.UserActivity;
import cz.hanusova.fingerprintgame.repository.InventoryRepository;
import cz.hanusova.fingerprintgame.repository.MaterialRepository;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.InventoryService;

/**
 * @author khanusova
 *
 */
@Service
public class InventoryServiceImpl implements InventoryService {
	private static final Log logger = LogFactory.getLog(InventoryServiceImpl.class);

	private static final String GOLD = "GOLD";
	private static final String STONE = "STONE";
	private static final String WOOD = "WOOD";
	private static final String WORKER = "WORKER";
	private static final String FOOD = "FOOD";

	private MaterialRepository materialRepository;
	private InventoryRepository inventoryRepository;
	private UserRepository userRepository;

	@Autowired
	public InventoryServiceImpl(MaterialRepository materialRepository, InventoryRepository inventoryRepository,
			UserRepository userRepository) {
		this.materialRepository = materialRepository;
		this.inventoryRepository = inventoryRepository;
		this.userRepository = userRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cz.hanusova.fingerprintgame.service.impl.InventoryService#
	 * updateWorkerAmount(java.lang.Float,
	 * cz.hanusova.fingerprintgame.model.AppUser)
	 */
	@Override
	public BigDecimal updateWorkerAmount(Float workerAmount, AppUser user) {
		return updateMaterialAmount(WORKER, workerAmount, user);
	}

	@Override
	public BigDecimal updateStoneAmount(Float amount, AppUser user) {
		return updateMaterialAmount(STONE, amount, user);
	}

	@Override
	public BigDecimal updateWoodAmount(Float amount, AppUser user) {
		return updateMaterialAmount(WOOD, amount, user);
	}

	@Override
	public BigDecimal updateFoodAmount(Float amount, AppUser user) {
		return updateMaterialAmount(FOOD, amount, user);
	}

	@Override
	public BigDecimal updateGoldAmount(Float amount, AppUser user) {
		return updateMaterialAmount(GOLD, amount, user);
	}

	private BigDecimal updateMaterialAmount(String materialName, Float amount, AppUser user) {
		Material material = materialRepository.findByName(materialName);
		List<Inventory> userInventory = user.getInventory();
		Inventory materialInventory = userInventory.stream().filter(i -> i.getMaterial().equals(material)).findAny()
				.orElse(null);
		if (materialInventory != null) {
			BigDecimal actualAmount = materialInventory.getAmount();
			BigDecimal newAmount = actualAmount.subtract(new BigDecimal(amount));
			materialInventory.setAmount(newAmount);
			inventoryRepository.save(materialInventory);

			return newAmount;
		}
		return null;
	}

	@Override
	public void addMining(Place place, AppUser user, float workers) {
		Material material = place.getMaterial();
		for (Inventory inventory : user.getInventory()) {
			if (inventory.getMaterial().equals(material)) {
				logger.info("Adding " + workers + " of " + material.getName() + " to " + user.getUsername());
				BigDecimal actualAmount = inventory.getAmount();
				inventory.setAmount(actualAmount.add(new BigDecimal(workers)));
				inventoryRepository.save(inventory);
				break;
			}
		}
	}

	@Override
	public void payRent(UserActivity activity, AppUser user) {
		float housesAmount = activity.getMaterialAmount();
		logger.info("User " + user.getUsername() + " is paying rent for " + housesAmount + " houses");
		updateGoldAmount(0.5f * housesAmount, user);
	}

	@Override
	public boolean feedWorkers(float workers, AppUser user) {
		logger.info("Giving food to workers");
		BigDecimal foodAmount = updateFoodAmount(workers * 0.25f, user);

		return true;
	}

}
