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
	 * Adds {@link Item} to user's item list. If user actually has some item of
	 * the same type, this method deletes it
	 * 
	 * @param user
	 * @param item
	 * @return updated {@link AppUser}
	 */
	AppUser addItem(AppUser user, Item item);

	/**
	 * Gets items that user can buy
	 * 
	 * @param user
	 * @return
	 */
	List<Item> getItemsForUser(AppUser user);

}
