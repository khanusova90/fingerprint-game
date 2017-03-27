/**
 * 
 */
package cz.hanusova.fingerprintgame.builder;

import cz.hanusova.fingerprintgame.model.Item;
import cz.hanusova.fingerprintgame.model.ItemType;

/**
 * @author khanusova
 *
 */
public class ItemBuilder {

	public Item build(int level) {
		Item item = new Item();
		item.setLevel(level);
		item.setItemType(createType());
		return item;
	}

	private ItemType createType() {
		ItemType type = new ItemType();
		type.setIdItemType(1l);
		type.setPrice(100);
		return type;
	}

}
