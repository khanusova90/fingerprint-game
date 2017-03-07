/**
 * 
 */
package cz.hanusova.fingerprintgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.hanusova.fingerprintgame.model.Item;
import cz.hanusova.fingerprintgame.model.ItemType;

/**
 * @author khanusova
 *
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

	public Item findByItemTypeAndLevel(ItemType itemType, Integer level);

}
