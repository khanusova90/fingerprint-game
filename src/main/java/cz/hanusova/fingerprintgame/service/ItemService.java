/**
 * 
 */
package cz.hanusova.fingerprintgame.service;

import java.util.List;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Item;

/**
 * @author khanusova
 *
 */
public interface ItemService {

	/**
	 * Adds {@link Item}s to user's item list. If user actually has some item of
	 * the same type, this method deletes it
	 * 
	 * @param items
	 * @return updated {@link AppUser}
	 */
	AppUser addItem(List<Item> items);

	/**
	 * Gets items that user can buy
	 * 
	 * @return
	 */
	List<Item> getItemsForActualUser();

}
