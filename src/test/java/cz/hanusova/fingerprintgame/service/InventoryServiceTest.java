/**
 * 
 */
package cz.hanusova.fingerprintgame.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Inventory;
import cz.hanusova.fingerprintgame.model.Material;
import cz.hanusova.fingerprintgame.repository.InventoryRepository;
import cz.hanusova.fingerprintgame.repository.MaterialRepository;
import cz.hanusova.fingerprintgame.repository.UserActivityRepository;
import cz.hanusova.fingerprintgame.service.impl.InventoryServiceImpl;

/**
 * @author khanusova
 *
 */
public class InventoryServiceTest {

	private InventoryService inventoryService;

	@Mock
	UserActivityRepository userActivityRepository;
	@Mock
	MaterialRepository materialRepository;
	@Mock
	InventoryRepository inventoryRepository;

	@Mock
	AppUser user;
	@Mock
	Inventory inventoryMock;
	@Mock
	Material gold;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		inventoryService = new InventoryServiceImpl(materialRepository, inventoryRepository, userActivityRepository);
		Mockito.when(materialRepository.findByName(Mockito.anyString())).thenReturn(gold);
		Mockito.when(inventoryMock.getMaterial()).thenReturn(gold);
		Mockito.when(inventoryMock.getAmount()).thenReturn(new BigDecimal("2.5"));
		List<Inventory> userInventory = new ArrayList<>();
		userInventory.add(inventoryMock);
		Mockito.when(user.getInventory()).thenReturn(userInventory);
	}

	@Test
	public void hasEnoughGoldTest() {
		boolean hasGold = inventoryService.hasEnoughGold(8f, user);
		Assert.assertFalse("User should not have enough gold to build house", hasGold);
	}

}
