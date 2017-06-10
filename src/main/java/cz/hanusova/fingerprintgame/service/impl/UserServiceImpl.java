package cz.hanusova.fingerprintgame.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.hanusova.fingerprintgame.dto.UserDTO;
import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Character;
import cz.hanusova.fingerprintgame.model.Inventory;
import cz.hanusova.fingerprintgame.model.Material;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.Role;
import cz.hanusova.fingerprintgame.repository.MaterialRepository;
import cz.hanusova.fingerprintgame.repository.PlaceRepository;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.UserService;
import cz.hanusova.fingerprintgame.utils.UserUtils;

@Service("userService")
public class UserServiceImpl implements UserService {
	private static Log logger = LogFactory.getLog(UserServiceImpl.class);

	private static final int FIRST_LEVEL = 5;
	private static final float LEVEL_COEF = 1.2f;

	private UserRepository userRepository;
	private MaterialRepository materialRepository;
	private PlaceRepository placeRepository;

	private PasswordEncoder encoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, MaterialRepository materialRepository,
			PlaceRepository placeRepository) {
		this.encoder = new BCryptPasswordEncoder();
		this.userRepository = userRepository;
		this.materialRepository = materialRepository;
		this.placeRepository = placeRepository;
	}

	@Transactional
	@Override
	public Boolean saveUser(AppUser user) {
		AppUser existingUser = userRepository.findByUsername(user.getUsername());

		if (existingUser == null) {
			logger.info("Saving new user: " + user.getUsername());
			String encPass = encoder.encode(user.getPassword());
			user.setPassword(encPass);
			user.getRoles().add(Role.ROLE_USER);
			user.setCharacter(new Character());
			createInventory(user);
			userRepository.save(user);
			return true;
		} else {
			return false;
		}
	}

	private void createInventory(AppUser user) {
		List<Material> materials = materialRepository.findAll();
		List<Inventory> userInventory = user.getInventory();

		for (Material m : materials) {
			Inventory inv = new Inventory(m);
			userInventory.add(inv);
		}

		userRepository.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Inventory> getUserInventory() {
		AppUser user = getActualUser();
		List<Inventory> result = user.getInventory();
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public AppUser getUserByName(String username) {
		AppUser user = userRepository.findByUsername(username);
		return user;
	}

	@Override
	@Transactional
	public UserDTO getUserDTOByUsername(String username) {
		AppUser user = getUserByName(username);
		return getUserDTOWithInfo(user);
	}

	@Override
	@Transactional
	public UserDTO getUserDTOByUsernameAndPassword(String username, String password) {
		AppUser user = getUserByName(username);
		if (user == null) {
			user = createUser(username, password);
		} else {
			user.setPassword(encoder.encode(password));
			userRepository.save(user);
		}

		return getUserDTOWithInfo(user);
	}

	private UserDTO getUserDTOWithInfo(AppUser user) {
		ModelMapper mapper = new ModelMapper();
		UserDTO userDTO = mapper.map(user, UserDTO.class);
		List<Place> places = placeRepository.findAll();
		if (places.size() == 0) {
			userDTO.setPlaceProgress(100);
		} else {
			userDTO.setPlaceProgress(100 * user.getPlaces().size() / placeRepository.findAll().size());
		}
		int xp = user.getCharacter().getXp();
		if (xp != 0) {
			double level = Math.log(xp / FIRST_LEVEL) / Math.log(LEVEL_COEF);
			userDTO.setLevel((int) level + 1);
			userDTO.setLevelProgress((int) ((level % 1) * 100));
		}

		return userDTO;
	}

	private AppUser createUser(String username, String password) {
		AppUser user = new AppUser();
		logger.info("Saving new user: " + username);
		String encPass = encoder.encode(password);
		user.setUsername(username);
		user.setPassword(encPass);
		user.getRoles().add(Role.ROLE_USER);
		user.setCharacter(new Character());
		createInventory(user);
		userRepository.save(user);
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cz.hanusova.fingerprintgame.service.UserService#getActualUser()
	 */
	@Override
	@Transactional(readOnly = true)
	public AppUser getActualUser() {
		String username = UserUtils.getActualUsername();
		return getUserByName(username);
	}

}
