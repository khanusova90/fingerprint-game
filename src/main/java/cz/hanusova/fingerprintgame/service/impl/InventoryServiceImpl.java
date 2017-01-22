/**
 * 
 */
package cz.hanusova.fingerprintgame.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Inventory;
import cz.hanusova.fingerprintgame.model.Material;
import cz.hanusova.fingerprintgame.repository.InventoryRepository;
import cz.hanusova.fingerprintgame.repository.MaterialRepository;
import cz.hanusova.fingerprintgame.service.InventoryService;

/**
 * @author khanusova
 *
 */
@Service
public class InventoryServiceImpl implements InventoryService {

	private static final String GOLD = "GOLD";
	private static final String STONE = "STONE";
	private static final String WOOD = "WOOD";
	private static final String WORKER = "WORKER";
	private static final String FOOD = "FOOD";

	private MaterialRepository materialRepository;
	private InventoryRepository inventoryRepository;

	@Autowired
	public InventoryServiceImpl(MaterialRepository materialRepository, InventoryRepository inventoryRepository) {
		this.materialRepository = materialRepository;
		this.inventoryRepository = inventoryRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cz.hanusova.fingerprintgame.service.impl.InventoryService#
	 * updateWorkerAmount(java.lang.Float,
	 * cz.hanusova.fingerprintgame.model.AppUser)
	 */
	@Override
	public void updateWorkerAmount(Float workerAmount, AppUser user) {
		updateMaterialAmount(WORKER, workerAmount, user);
		// Material worker = materialRepository.findWorker();
		// List<Inventory> userInventory = user.getInventory();
		// Inventory workers = userInventory.stream().filter(i ->
		// i.getMaterial().equals(worker)).findAny().orElse(null);
		//
		// BigDecimal actualAmount = workers.getAmount();
		// workers.setAmount(actualAmount.subtract(new
		// BigDecimal(workerAmount)));
		// inventoryRepository.save(workers);
	}

	@Override
	public void updateStoneAmount(Float amount, AppUser user) {
		updateMaterialAmount(STONE, amount, user);
	}

	@Override
	public void updateWoodAmount(Float amount, AppUser user) {
		updateMaterialAmount(WOOD, amount, user);
	}

	private void updateMaterialAmount(String materialName, Float amount, AppUser user) {
		Material material = materialRepository.findByName(materialName);
		List<Inventory> userInventory = user.getInventory();
		Inventory materialInventory = userInventory.stream().filter(i -> i.getMaterial().equals(material)).findAny()
				.orElse(null);
		BigDecimal actualAmount = materialInventory.getAmount();
		materialInventory.setAmount(actualAmount.subtract(new BigDecimal(amount)));
		inventoryRepository.save(materialInventory);
	}

}
