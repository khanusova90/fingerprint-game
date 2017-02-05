/**
 * 
 */
package cz.hanusova.fingerprintgame.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Item;
import cz.hanusova.fingerprintgame.model.ItemType;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.ItemService;

/**
 * @author khanusova
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	private static final Log logger = LogFactory.getLog(ItemServiceImpl.class);

	private UserRepository userRepository;

	/**
	 * 
	 */
	@Autowired
	public ItemServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public AppUser addItem(AppUser user, Item item) {
		deleteActualItem(user, item.getItemType());
		logger.info("Adding item " + item.getName() + " to user " + user.getUsername());
		user.getItems().add(item);
		userRepository.save(user);

		return user;
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
