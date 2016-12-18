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

import cz.hanusova.fingerprintgame.builder.UserBuilder;
import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.UserActivity;
import cz.hanusova.fingerprintgame.repository.InventoryRepository;
import cz.hanusova.fingerprintgame.repository.MaterialRepository;
import cz.hanusova.fingerprintgame.repository.PlaceRepository;
import cz.hanusova.fingerprintgame.repository.UserActivityRepository;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.impl.ActivityServiceImpl;
import cz.hanusova.fingerprintgame.service.impl.InventoryServiceImpl;
import cz.hanusova.fingerprintgame.service.impl.PlaceServiceImpl;

/**
 * @author khanusova
 *
 */
public class PlaceServiceTest {

	private PlaceServiceImpl placeService;

	@Mock
	private PlaceRepository placeRepositoryMock;
	@Mock
	private UserActivityRepository userActivityRepositoryMock;
	@Mock
	private MaterialRepository materialRepositoryMock;
	@Mock
	private InventoryRepository inventoryRepositoryMock;
	@Mock
	private UserRepository userRepositoryMock;
	@Mock
	private Place placeMock;

	private UserBuilder userBuilder = new UserBuilder();
	private AppUser user;

	private static final Float MATERIAL_AMOUNT_20 = 20f;
	private static final Float MATERIAL_AMOUNT_30 = 30f;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		InventoryService inventoryService = new InventoryServiceImpl(materialRepositoryMock, inventoryRepositoryMock);
		ActivityService activityService = new ActivityServiceImpl(userActivityRepositoryMock, userRepositoryMock,
				inventoryService);
		placeService = new PlaceServiceImpl(placeRepositoryMock, activityService, userRepositoryMock);
		user = userBuilder.build();
		Mockito.when(materialRepositoryMock.findWorker())
				.thenReturn(user.getInventory().iterator().next().getMaterial());
	}

	@Test
	public void startActivityTest() {
		int activCount = user.getActivities().size();
		List<UserActivity> activities = placeService.startActivity(user, placeMock, MATERIAL_AMOUNT_20);

		Assert.assertNotNull("List of returned activities should not be null", activities);
		Assert.assertNotEquals("Activity should be added or removed from user's activities list", activCount,
				activities.size());
	}

	@Test
	public void startNewActivityTest() {
		int activCount = user.getActivities().size();
		List<UserActivity> activities = placeService.startActivity(user, placeMock, MATERIAL_AMOUNT_20);

		Assert.assertFalse("Returned list should contain at least one activity", activities.isEmpty());
		Assert.assertEquals("New activity should be added to list", activCount + 1, activities.size());
	}

	@Test
	public void stopActivityTest() {
		createExistingActivity();

		int activCount = user.getActivities().size();
		List<UserActivity> activities = placeService.startActivity(user, placeMock, 0f);

		Assert.assertNotEquals("Activities count should change", activCount, activities.size());
		Assert.assertEquals("Existing activity should be removed from list", activCount - 1, activities.size());
	}

	private void createExistingActivity() {
		UserActivity activityMock = Mockito.mock(UserActivity.class);
		Mockito.when(activityMock.getPlace()).thenReturn(placeMock);
		Mockito.when(activityMock.getMaterialAmount()).thenReturn(MATERIAL_AMOUNT_20);

		List<UserActivity> activitiesMock = new ArrayList<>();
		activitiesMock.add(activityMock);
		user.setActivities(activitiesMock);
	}

	@Test
	public void changeMaterialAmountTest() {
		createExistingActivity();

		int activCount = user.getActivities().size();
		List<UserActivity> activities = placeService.startActivity(user, placeMock, MATERIAL_AMOUNT_30);

		Assert.assertEquals("Number of existing activities should not change", activCount, activities.size());
	}

	@Test
	public void inventoryChangeTest() {
		createExistingActivity();
		BigDecimal workers = user.getInventory().iterator().next().getAmount();

		placeService.startActivity(user, placeMock, MATERIAL_AMOUNT_30);
		BigDecimal workersUpdate = user.getInventory().iterator().next().getAmount();

		Assert.assertNotEquals("Amount of workers should change", workers, workersUpdate);
	}

}
