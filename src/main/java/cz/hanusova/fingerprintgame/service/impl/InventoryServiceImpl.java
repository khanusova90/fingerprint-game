/**
 * 
 */
package cz.hanusova.fingerprintgame.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.hanusova.fingerprintgame.model.ActivityEnum;
import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Inventory;
import cz.hanusova.fingerprintgame.model.Item;
import cz.hanusova.fingerprintgame.model.ItemType;
import cz.hanusova.fingerprintgame.model.Material;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.UserActivity;
import cz.hanusova.fingerprintgame.repository.InventoryRepository;
import cz.hanusova.fingerprintgame.repository.MaterialRepository;
import cz.hanusova.fingerprintgame.repository.UserActivityRepository;
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

	/**
	 * Amount of food that gets every worker for work
	 */
	private static final Float FOOD_FOR_WORK = 0.25f;
	/**
	 * Amount of material that every worker mines
	 */
	private static final Float MINING_COEF = 1f;

	/**
	 * Amount of gold for paying rent for each worker
	 */
	private static final Float GOLD_RENT = 0.25f;

	/**
	 * Value indicating how much will each level of item increase mining
	 */
	private static final float LEVEL_INCREASE = 0.1f;

	private MaterialRepository materialRepository;
	private InventoryRepository inventoryRepository;
	private UserActivityRepository userActivityRepository;

	@Autowired
	public InventoryServiceImpl(MaterialRepository materialRepository, InventoryRepository inventoryRepository,
			UserActivityRepository userActivityRepository) {
		this.materialRepository = materialRepository;
		this.inventoryRepository = inventoryRepository;
		this.userActivityRepository = userActivityRepository;
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

	/**
	 * Updates amount of material in user's inventory by subtracting given
	 * amount
	 * 
	 * @param materialName
	 *            name of material to update
	 * @param amount
	 *            amount of material to subtract. Pass negative parameter value
	 *            to increase material amount in inventory
	 * @param user
	 * @return new material amount
	 */
	private BigDecimal updateMaterialAmount(String materialName, Float amount, AppUser user) {
		Inventory materialInventory = getUserInventory(user, materialName);
		if (materialInventory != null) {
			return updateMaterialAmount(materialInventory, amount, user);
		}
		return null;
	}

	/**
	 * Updates amount of material in user's inventory by subtracting given
	 * amount
	 * 
	 * @param materialInventory
	 *            {@link Inventory} to update
	 * @param amount
	 *            amount of material to subtract. Pass negative parameter value
	 *            to increase material amount in inventory
	 * @param user
	 * @return new material amount
	 */
	private BigDecimal updateMaterialAmount(Inventory materialInventory, Float amount, AppUser user) {
		BigDecimal actualAmount = materialInventory.getAmount();
		BigDecimal newAmount = actualAmount.subtract(new BigDecimal(amount));
		materialInventory.setAmount(newAmount);
		inventoryRepository.save(materialInventory);

		return newAmount;
	}

	private Inventory getUserInventory(AppUser user, String materialName) {
		Material material = materialRepository.findByName(materialName);
		return getUserInventory(user, material);
	}

	private Inventory getUserInventory(AppUser user, Material material) {
		List<Inventory> userInventory = user.getInventory();
		return userInventory.stream().filter(i -> i.getMaterial().equals(material)).findAny().orElse(null);
	}

	@Override
	@Transactional
	public void mine(Place place, AppUser user, float workers) {
		updateFoodAmount(workers * FOOD_FOR_WORK, user);
		mineMaterial(place.getMaterial(), user, workers);
	}

	private void mineMaterial(Material material, AppUser user, float workers) {
		Inventory inventory = getUserInventory(user, material);
		Float minedAmount = workers * getMiningCoef(user, material);
		logger.info("Adding " + minedAmount + " of " + material.getName() + " to " + user.getUsername());

		BigDecimal actualAmount = inventory.getAmount();
		inventory.setAmount(actualAmount.add(new BigDecimal(minedAmount)));
		inventoryRepository.save(inventory);
	}

	private float getMiningCoef(AppUser user, Material material) {
		float coef = MINING_COEF;
		List<Item> miningItems = getMiningItems(user, material);
		for (Item item : miningItems) {
			coef += item.getLevel() * LEVEL_INCREASE;
		}
		return coef;
	}

	private List<Item> getMiningItems(AppUser user, Material material) {
		List<Item> userItems = user.getItems();
		return userItems.stream().filter(item -> {
			ItemType type = item.getItemType();
			return ActivityEnum.MINE.equals(type.getActivity()) && material.equals(type.getMaterial());
		}).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void payRent(UserActivity activity, AppUser user) {
		float housesAmount = activity.getMaterialAmount();
		logger.info("User " + user.getUsername() + " is paying rent for " + housesAmount + " houses");
		updateGoldAmount(0.5f * housesAmount, user);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean hasEnoughFood(float workers, AppUser user) {
		Inventory foodInventory = getUserInventory(user, FOOD);
		BigDecimal amount = foodInventory.getAmount();
		return amount.compareTo(new BigDecimal(workers * FOOD_FOR_WORK)) != -1;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean hasEnoughGold(float workers, AppUser user) {
		Inventory goldInventory = getUserInventory(user, GOLD);
		BigDecimal amount = goldInventory.getAmount();
		return amount.compareTo(new BigDecimal(workers * GOLD_RENT)) != -1;
	}

	@Override
	@Transactional
	public void stopBuilding(UserActivity activity, AppUser user) {
		Float workersAmount = activity.getMaterialAmount();
		Inventory workers = getUserInventory(user, WORKER);
		updateMaterialAmount(workers, workersAmount, user);
		userActivityRepository.delete(activity);
	}

}
