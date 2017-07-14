/**
 * 
 */
package cz.hanusova.fingerprintgame.test.integration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import cz.hanusova.fingerprintgame.builder.UserBuilder;
import cz.hanusova.fingerprintgame.model.ActivityEnum;
import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Inventory;
import cz.hanusova.fingerprintgame.model.Material;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.PlaceType;
import cz.hanusova.fingerprintgame.model.UserActivity;
import cz.hanusova.fingerprintgame.repository.InventoryRepository;
import cz.hanusova.fingerprintgame.repository.MaterialRepository;
import cz.hanusova.fingerprintgame.repository.UserActivityRepository;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.ActivityService;
import cz.hanusova.fingerprintgame.service.InventoryService;
import cz.hanusova.fingerprintgame.service.impl.ActivityServiceImpl;
import cz.hanusova.fingerprintgame.service.impl.InventoryServiceImpl;

/**
 * @author khanusova
 *
 */
public class StopBuildTest {

	private ActivityService activityService;
	private InventoryService inventoryService;

	@Mock
	UserActivityRepository userActivityRepository;
	@Mock
	UserRepository userRepository;
	@Mock
	MaterialRepository materialRepository;
	@Mock
	InventoryRepository inventoryRepository;
	@Mock
	Place placeMock;
	@Mock
	PlaceType placeTypeMock;
	@Mock
	UserActivity buildActivity;
	@Mock
	Inventory inventoryMock;
	@Mock
	Material gold;

	private AppUser user;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		inventoryService = new InventoryServiceImpl(materialRepository, inventoryRepository, userActivityRepository);
		activityService = new ActivityServiceImpl(userActivityRepository, userRepository, inventoryService);
		UserBuilder userBuilder = new UserBuilder();
		user = userBuilder.build();
		Mockito.when(placeMock.getPlaceType()).thenReturn(placeTypeMock);
		Mockito.when(placeTypeMock.getActivity()).thenReturn(ActivityEnum.BUILD);
		Mockito.when(userRepository.findOne(Mockito.anyLong())).thenReturn(user);
		List<AppUser> users = new ArrayList<>();
		users.add(user);
		Mockito.when(userRepository.findAll()).thenReturn(users);
		Mockito.when(buildActivity.getMaterialAmount()).thenReturn(8f);
		Mockito.when(buildActivity.getPlace()).thenReturn(placeMock);
		List<UserActivity> activities = new ArrayList<>();
		activities.add(buildActivity);
		user.setActivities(activities);

		Mockito.when(materialRepository.findByName(Mockito.anyString())).thenReturn(gold);
		Mockito.when(inventoryMock.getMaterial()).thenReturn(gold);
		Mockito.when(inventoryMock.getAmount()).thenReturn(new BigDecimal("2.5"));
		List<Inventory> userInventory = new ArrayList<>();
		userInventory.add(inventoryMock);
		user.setInventory(userInventory);

	}

	@Test
	public void stopBuildTest() {
		activityService.checkRunningActivities();
	}

}
