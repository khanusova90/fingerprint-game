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
import cz.hanusova.fingerprintgame.dto.UserDTO;
import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.repository.MaterialRepository;
import cz.hanusova.fingerprintgame.repository.PlaceRepository;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.impl.UserServiceImpl;

/**
 * @author khanusova
 *
 */
public class UserServiceTest {
	private UserService userService;
	private UserBuilder userBuilder = new UserBuilder();

	@Mock
	UserRepository userRepositoryMock;
	@Mock
	MaterialRepository materialRepositoryMock;
	@Mock
	PlaceRepository placeRepositoryMock;

	AppUser userMock;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		userService = new UserServiceImpl(userRepositoryMock, materialRepositoryMock, placeRepositoryMock);
		userMock = userBuilder.build();
		Mockito.when(userRepositoryMock.findByUsername(Mockito.anyString())).thenReturn(userMock);
	}

	@Test
	public void fillPropertiesTest() {
		UserDTO user = userService.getUserDTOByUsername("username");
		Assert.assertNotEquals("Level should be calculated", 0, user.getLevel());
		Assert.assertNotEquals("Level progress should be calculated", 0, user.getLevelProgress());
		Assert.assertNotEquals("Place progress should be calculated", 0, user.getPlaceProgress());
	}

	@Test
	public void testUserLevel() {
		userMock.getCharacter().setXp(6);
		UserDTO user = userService.getUserDTOByUsername("username");
		Assert.assertEquals("User with 6 XP should be in second level", 2, user.getLevel());
		Assert.assertNotEquals("Level progress should be set", 0, user.getLevelProgress());
	}

}
