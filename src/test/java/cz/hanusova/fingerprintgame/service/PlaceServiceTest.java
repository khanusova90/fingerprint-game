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
import cz.hanusova.fingerprintgame.model.ActivityEnum;
import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.PlaceType;
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
	@Mock
	private PlaceType placeTypeMock;

	private UserBuilder userBuilder = new UserBuilder();
	private AppUser user;

	private static final Float MATERIAL_AMOUNT_20 = 20f;
	private static final Float MATERIAL_AMOUNT_30 = 30f;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		InventoryService inventoryService = new InventoryServiceImpl(materialRepositoryMock, inventoryRepositoryMock,
				userActivityRepositoryMock);
		ActivityService activityService = new ActivityServiceImpl(userActivityRepositoryMock, userRepositoryMock,
				inventoryService);
		placeService = new PlaceServiceImpl(placeRepositoryMock, activityService, userRepositoryMock);
		user = userBuilder.build();
		Mockito.when(materialRepositoryMock.findWorker())
				.thenReturn(user.getInventory().iterator().next().getMaterial());
		Mockito.when(materialRepositoryMock.findByName(Mockito.anyString()))
				.thenReturn(user.getInventory().iterator().next().getMaterial());
		Mockito.when(placeMock.getPlaceType()).thenReturn(placeTypeMock);
		Mockito.when(placeTypeMock.getActivity()).thenReturn(ActivityEnum.MINE);
	}

	@Test
	public void startActivityTest() {
		int activCount = user.getActivities().size();
		placeService.startActivity(user, placeMock, MATERIAL_AMOUNT_20);
		List<UserActivity> activities = user.getActivities();
		Assert.assertNotNull("List of returned activities should not be null", activities);
		Assert.assertNotEquals("Activity should be added or removed from user's activities list", activCount,
				activities.size());
	}

	@Test
	public void startNewActivityTest() {
		int activCount = user.getActivities().size();
		placeService.startActivity(user, placeMock, MATERIAL_AMOUNT_20);
		List<UserActivity> activities = user.getActivities();
		Assert.assertFalse("Returned list should contain at least one activity", activities.isEmpty());
		Assert.assertEquals("New activity should be added to list", activCount + 1, activities.size());
	}

	@Test
	public void stopActivityTest() {
		createExistingActivity();

		int activCount = user.getActivities().size();
		placeService.startActivity(user, placeMock, 0f);
		List<UserActivity> activities = user.getActivities();
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
		placeService.startActivity(user, placeMock, MATERIAL_AMOUNT_30);
		List<UserActivity> activities = user.getActivities();
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

	/**
	 * Checks if created activity has all mandatory properties
	 */
	@Test
	public void attributesTest() {
		// New activity
		int activCount = user.getActivities().size();
		placeService.startActivity(user, placeMock, MATERIAL_AMOUNT_20);
		List<UserActivity> activities = user.getActivities();
		Assert.assertEquals("New activity should be added to list", activCount + 1, activities.size());

		UserActivity newActivity = activities.get(activities.size() - 1);
		Assert.assertNotNull("Place should be filled in new activity", newActivity.getPlace());
		Assert.assertNotNull("Start time should be filled in new activity", newActivity.getStartTime());

		// Activity update
		placeService.startActivity(user, placeMock, MATERIAL_AMOUNT_30);
		activities = user.getActivities();
		Assert.assertEquals("New activity should not be added to list", activCount + 1, activities.size());
		newActivity = activities.get(activities.size() - 1);
		Assert.assertNotNull("Place should be filled in new activity", newActivity.getPlace());
		Assert.assertNotNull("Start time should be filled in new activity", newActivity.getStartTime());
	}

}
