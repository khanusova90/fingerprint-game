/**
 * 
 */
package cz.hanusova.fingerprintgame.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Item;
import cz.hanusova.fingerprintgame.model.ItemType;
import cz.hanusova.fingerprintgame.repository.ItemRepository;
import cz.hanusova.fingerprintgame.repository.ItemTypeRepository;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.InventoryService;
import cz.hanusova.fingerprintgame.service.ItemService;
import cz.hanusova.fingerprintgame.service.UserService;

/**
 * @author khanusova
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	private static final Log logger = LogFactory.getLog(ItemServiceImpl.class);

	private static final Float ITEM_PRICE = 50f;

	private UserRepository userRepository;
	private ItemRepository itemRepository;
	private ItemTypeRepository itemTypeRepository;
	private InventoryService inventoryService;
	private UserService userService;

	/**
	 * 
	 */
	@Autowired
	public ItemServiceImpl(UserRepository userRepository, ItemTypeRepository itemTypeRepository,
			ItemRepository itemRepository, InventoryService inventoryService, UserService userService) {
		this.userRepository = userRepository;
		this.itemTypeRepository = itemTypeRepository;
		this.itemRepository = itemRepository;
		this.inventoryService = inventoryService;
		this.userService = userService;
	}

	@Override
	@Transactional
	public AppUser addItem(Item item) {
		AppUser user = userService.getActualUser();
		deleteActualItem(user, item.getItemType());
		logger.info("Adding item " + item.getName() + " to user " + user.getUsername());
		user.getItems().add(item);
		inventoryService.updateGoldAmount(ITEM_PRICE * item.getLevel(), user);
		userRepository.save(user);

		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Item> getItemsForActualUser() {
		AppUser user = userService.getActualUser();
		List<Item> items = new ArrayList<>();
		List<ItemType> itemTypes = itemTypeRepository.findAll();
		for (ItemType type : itemTypes) {
			Item userItem = findItemByType(user, type);
			if (userItem != null) {
				Item item = itemRepository.findByItemTypeAndLevel(type, userItem.getLevel() + 1);
				if (item != null) {
					items.add(item);
				}
				continue;
			}
			items.add(itemRepository.findByItemTypeAndLevel(type, 1)); // TODO:
																		// vrati
																		// null
		}
		return items;
	}

	private void deleteActualItem(AppUser user, ItemType type) {
		logger.info("Deleting item " + type.getName() + " from user " + user.getUsername());
		Item item = findItemByType(user, type);
		if (item != null) {
			user.getItems().remove(item);
		}
	}

	private Item findItemByType(AppUser user, ItemType type) {
		List<Item> items = user.getItems();
		return items.stream().filter(i -> type.equals(i.getItemType())).findAny().orElse(null);
	}

}
