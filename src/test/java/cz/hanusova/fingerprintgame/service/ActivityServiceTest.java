/**
 * 
 */
package cz.hanusova.fingerprintgame.service;

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
import cz.hanusova.fingerprintgame.repository.UserActivityRepository;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.impl.ActivityServiceImpl;

/**
 * @author khanusova
 *
 */
public class ActivityServiceTest {

	private ActivityService activityService;

	@Mock
	UserActivityRepository userActivityRepository;
	@Mock
	UserRepository userRepository;
	@Mock
	InventoryService inventoryService;

	@Mock
	Place placeMock;
	@Mock
	PlaceType placeTypeMock;
	AppUser user;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.activityService = new ActivityServiceImpl(userActivityRepository, userRepository, inventoryService);
		UserBuilder userBuilder = new UserBuilder();
		user = userBuilder.build();
		Mockito.when(placeMock.getPlaceType()).thenReturn(placeTypeMock);
		Mockito.when(placeTypeMock.getActivity()).thenReturn(ActivityEnum.MINE);
	}

	@Test
	public void increaseXpTest() {
		int xp = user.getCharacter().getXp();
		activityService.startNewActivity(placeMock, 10f, user);
		Assert.assertNotEquals("XP should be increased after activity start", xp, user.getCharacter().getXp());
	}

}
