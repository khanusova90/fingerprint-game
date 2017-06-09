/**
 * 
 */
package cz.hanusova.fingerprintgame.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Character;
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
	 * @return new {@link AppUser} with:
	 *         <ul>
	 *         <li>100 workers in inventory</li>
	 *         <li>10XP</li>
	 *         </ul>
	 */
	public AppUser build() {
		AppUser user = new AppUser();
		user.setInventory(createInventory());
		user.setCharacter(createCharacter());
		return user;
	}

	private List<Inventory> createInventory() {
		List<Inventory> inventory = new ArrayList<>();
		Material worker = new Material();
		worker.setName("WORKER");
		inventory.add(new Inventory(worker, new BigDecimal("100")));

		return inventory;
	}

	private Character createCharacter() {
		Character character = new Character();
		character.setXp(50);
		return character;
	}

}
