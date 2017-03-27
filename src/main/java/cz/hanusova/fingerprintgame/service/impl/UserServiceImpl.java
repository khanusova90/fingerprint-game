package cz.hanusova.fingerprintgame.service.impl;

import java.util.Base64;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import cz.hanusova.fingerprintgame.dto.UserDTO;
import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Character;
import cz.hanusova.fingerprintgame.model.Inventory;
import cz.hanusova.fingerprintgame.model.Material;
import cz.hanusova.fingerprintgame.model.Role;
import cz.hanusova.fingerprintgame.model.StagUser;
import cz.hanusova.fingerprintgame.model.TimetableAction;
import cz.hanusova.fingerprintgame.repository.MaterialRepository;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.UserService;
import cz.hanusova.fingerprintgame.utils.UserUtils;

@Service("userService")
public class UserServiceImpl implements UserService {
	private static Log logger = LogFactory.getLog(UserServiceImpl.class);

	private static final String STAG_URL = "https://stagws.uhk.cz/ws/services/rest/";

	private UserRepository userRepository;
	private MaterialRepository materialRepository;

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
	public UserDTO loginUser(String auth, String username) {
		try {
			RestTemplate template = new RestTemplate();
			List<StagUser> users = template
					.exchange(STAG_URL + "users/getOsobniCislaByExternalLogin?login=" + username + "&outputFormat=JSON",
							HttpMethod.GET, null, new ParameterizedTypeReference<List<StagUser>>() {
							})
					.getBody();
			if (users != null && users.size() > 0) {
				HttpHeaders headers = new HttpHeaders();
				headers.add("authorization", auth);

				HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
				List<TimetableAction> timetable = template.exchange(
						STAG_URL + "rozvrhy/getRozvrhByStudent?osCislo=" + users.get(0).getUserNumbers().get(0)
								+ "&outputFormat=JSON",
						HttpMethod.GET, entity, new ParameterizedTypeReference<List<TimetableAction>>() {
						}).getBody();
				if (timetable != null) {
					byte[] credentialsByte = Base64.getDecoder().decode(auth.substring(auth.indexOf(" ") + 1));
					String[] credentials = new String(credentialsByte).split(":");
					return getUserDTOByUsernameAndPassword(credentials[0], credentials[1]);
				}
			}
		} catch (HttpClientErrorException e) {
			logger.warn("Returned 401 from STAG");
		}
		return null;
	}

	@Override
	@Transactional
	public UserDTO getUserDTOByUsername(String username) {
		AppUser user = getUserByName(username);
		ModelMapper mapper = new ModelMapper();
		UserDTO userDTO = mapper.map(user, UserDTO.class);
		return userDTO;
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
		ModelMapper mapper = new ModelMapper();
		UserDTO userDTO = mapper.map(user, UserDTO.class);
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
