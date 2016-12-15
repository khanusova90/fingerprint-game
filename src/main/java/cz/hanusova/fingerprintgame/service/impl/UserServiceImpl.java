package cz.hanusova.fingerprintgame.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Character;
import cz.hanusova.fingerprintgame.model.Inventory;
import cz.hanusova.fingerprintgame.model.Material;
import cz.hanusova.fingerprintgame.model.Role;
import cz.hanusova.fingerprintgame.repository.MaterialRepository;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.UserService;
import cz.hanusova.fingerprintgame.utils.UserUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static Log logger = LogFactory.getLog(UserServiceImpl.class);

	private UserRepository userRepository;
	private MaterialRepository materialRepository;

	// @Value("${app.default.gold}")
	// private String goldAmount;
	//
	// @Value("${app.default.food}")
	// private String foodAmount;
	//
	// @Value("${app.default.wood}")
	// private String woodAmount;
	//
	// @Value("${app.default.stone}")
	// private String stoneAmount;
	//
	// @Value("${app.default.worker}")
	// private String workerAmount;

	private PasswordEncoder encoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, MaterialRepository materialRepository) {
		this.encoder = new BCryptPasswordEncoder();
		this.userRepository = userRepository;
		this.materialRepository = materialRepository;
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

	/**
	 * Prideli uzivateli pocatecni seznam surovin dle paramteru nastavenych v
	 * <code>application.properties</code>
	 * 
	 * @param user
	 *            {@link AppUser} novy uzivatel
	 */
	private void createInventory(AppUser user) {
		List<Material> materials = materialRepository.findAll();
		Set<Inventory> userInventory = user.getInventory();

		for (Material m : materials) {
			Inventory inv = new Inventory(m);
			userInventory.add(inv);
		}

		userRepository.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<Inventory> getUserInventory() {
		String username = UserUtils.getActualUsername();
		AppUser user = userRepository.findByUsername(username);
		Set<Inventory> result = user.getInventory();
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public AppUser getUserByName(String username) {
		AppUser user = userRepository.findByUsername(username);
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public AppUser getUserByUsernameWithRoles(String username) {
		AppUser user = userRepository.findByUsernameFetch(username);
		return user;
	}

}
