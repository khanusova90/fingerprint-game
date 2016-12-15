/**
 * 
 */
package cz.hanusova.fingerprintgame.builder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Inventory;
import cz.hanusova.fingerprintgame.model.Material;

/**
 * @author khanusova
 * 
 *         Class for creating new {@link AppUser} entities for testing purposes
 *
 */
public class UserBuilder {

	/**
	 * 
	 * @return new {@link AppUser} with 100 workers in inventory
	 */
	public AppUser build() {
		AppUser user = new AppUser();
		user.setInventory(createInventory());
		return user;
	}

	private Set<Inventory> createInventory() {
		Set<Inventory> inventory = new HashSet<>();
		Material worker = new Material();
		worker.setName("WORKER");
		inventory.add(new Inventory(worker, new BigDecimal("100")));

		return inventory;
	}

}
