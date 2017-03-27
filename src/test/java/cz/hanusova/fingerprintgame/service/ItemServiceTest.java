/**
 * 
 */
package cz.hanusova.fingerprintgame.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import cz.hanusova.fingerprintgame.builder.ItemBuilder;
import cz.hanusova.fingerprintgame.builder.UserBuilder;
import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Item;
import cz.hanusova.fingerprintgame.repository.ItemRepository;
import cz.hanusova.fingerprintgame.repository.ItemTypeRepository;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.impl.ItemServiceImpl;

/**
 * @author khanusova
 *
 */
public class ItemServiceTest {

	private ItemService itemService;

	@Mock
	private UserRepository userRepositoryMock;
	@Mock
	private ItemRepository itemRepositoryMock;
	@Mock
	private ItemTypeRepository itemTypeRepositoryMock;
	@Mock
	private InventoryService inventoryServiceMock;
	@Mock
	private UserService userServiceMock;

	private UserBuilder userBuilder;
	private ItemBuilder itemBuilder;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		itemService = new ItemServiceImpl(userRepositoryMock, itemTypeRepositoryMock, itemRepositoryMock,
				inventoryServiceMock, userServiceMock);
		userBuilder = new UserBuilder();
		itemBuilder = new ItemBuilder();
	}

	@Test
	public void addItemTest() {
		AppUser user = userBuilder.build();
		signInUser(user);
		int itemSize = user.getItems().size();
		List<Item> items = new ArrayList<>();
		items.add(itemBuilder.build(1));
		itemService.addItem(items);
		Assert.assertNotEquals("User should get new item to his item list", itemSize, user.getItems().size());
	}

	@Test
	public void addItemUpdateTest() {
		AppUser user = userBuilder.build();
		signInUser(user);
		Item item1 = itemBuilder.build(1);
		user.getItems().add(item1);
		Assert.assertEquals("User should have only one item", 1, user.getItems().size());
		List<Item> items = new ArrayList<>();
		items.add(itemBuilder.build(2));
		itemService.addItem(items);
		Assert.assertEquals("Size of item list should not change", 1, user.getItems().size());
	}

	@Test
	public void addItemInventoryUpdateTest() {
		AppUser user = userBuilder.build();
		signInUser(user);
		List<Item> items = new ArrayList<>();
		items.add(itemBuilder.build(1));
		itemService.addItem(items);
		Mockito.verify(inventoryServiceMock, Mockito.times(1)).updateGoldAmount(Mockito.anyFloat(), Mockito.any());
	}

	private void signInUser(AppUser user) {
		Mockito.when(userServiceMock.getActualUser()).thenReturn(user);
	}

}
