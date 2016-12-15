/**
 * 
 */
package cz.hanusova.fingerprintgame.service.impl;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Inventory;
import cz.hanusova.fingerprintgame.model.Material;
import cz.hanusova.fingerprintgame.repository.InventoryRepository;
import cz.hanusova.fingerprintgame.repository.MaterialRepository;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.InventoryService;

/**
 * @author khanusova
 *
 */
public class InventoryServiceImpl implements InventoryService {

	private MaterialRepository materialRepository;
	// private UserRepository userRepository;
	private InventoryRepository inventoryRepository;

	@Autowired
	public InventoryServiceImpl(MaterialRepository materialRepository, UserRepository userRepository,
			InventoryRepository inventoryRepository) {
		this.materialRepository = materialRepository;
		// this.userRepository = userRepository;
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
		Material worker = materialRepository.findWorker();
		Set<Inventory> userInventory = user.getInventory();
		Inventory workers = userInventory.stream().filter(i -> i.getMaterial().equals(worker)).findAny().orElse(null);

		BigDecimal actualAmount = workers.getAmount();
		workers.setAmount(actualAmount.subtract(new BigDecimal(workerAmount)));
		inventoryRepository.save(workers);
	}

}
