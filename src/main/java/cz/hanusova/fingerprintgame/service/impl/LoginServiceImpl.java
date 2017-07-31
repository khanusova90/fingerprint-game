/**
 * 
 */
package cz.hanusova.fingerprintgame.service.impl;

import java.util.Base64;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import cz.hanusova.fingerprintgame.dto.UserDTO;
import cz.hanusova.fingerprintgame.model.StagUser;
import cz.hanusova.fingerprintgame.model.TimetableAction;
import cz.hanusova.fingerprintgame.service.LoginService;
import cz.hanusova.fingerprintgame.service.UserService;

/**
 * @author khanusova
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	private static final Log logger = LogFactory.getLog(LoginServiceImpl.class);
	private static final Log loggingLogger = LogFactory.getLog("logging");

	private UserService userService;
	private RestTemplate template;

	private static final String STAG_URL = "https://stagws.uhk.cz/ws/services/rest/";

	@Autowired
	public LoginServiceImpl(UserService userService) {
		this.userService = userService;
		this.template = new RestTemplate();
	}

	@Override
	@Transactional
	public UserDTO loginUser(String auth, String username) {
		try {
			loggingLogger.info("Getting user " + username + " from STAG");
			byte[] credentialsByte = Base64.getDecoder().decode(auth.substring(auth.indexOf(" ") + 1));
			String[] credentials = new String(credentialsByte).split(":");
			return userService.getUserDTOByUsernameAndPassword(credentials[0], credentials[1]);
			// List<StagUserInfo> users = template.exchange(
			// STAG_URL + "users/getStagUserListForExternalLogin?externalLogin="
			// + username + "&outputFormat=JSON",
			// HttpMethod.GET, null, new
			// ParameterizedTypeReference<List<StagUserInfo>>() {
			// }).getBody();
			// if (users != null && !users.isEmpty() && users.get(0).getUsers()
			// != null
			// && !users.get(0).getUsers().isEmpty()) {
			//
			// return authorizeUser(auth, username, users.get(0).getUsers());
			// } else {
			// loggingLogger.info("User " + username + " was not found in
			// STAG");
			// }
		} catch (HttpClientErrorException e) {
			logger.warn("Returned 401 from STAG");
			loggingLogger.warn("Returned 401 from STAG");
		}
		return null;
	}

	private UserDTO authorizeUser(String auth, String username, List<StagUser> users) {
		loggingLogger.info("Trying to authorize user " + username);

		HttpHeaders headers = new HttpHeaders();
		headers.add("authorization", auth);

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		for (StagUser user : users) {
			switch (user.getRole()) {
			case "ST":
				return authorizeStudent(username, auth, entity);
			case "VY":
				return authorizeTeacher(auth, user.getUcitIdNo(), entity);
			default:
				break;
			}
		}
		return null;
	}

	private UserDTO authorizeStudent(String username, String auth, HttpEntity<String> entity) {
		List<StagUser> students = template
				.exchange(STAG_URL + "users/getOsobniCislaByExternalLogin?login=" + username + "&outputFormat=JSON",
						HttpMethod.GET, null, new ParameterizedTypeReference<List<StagUser>>() {
						})
				.getBody();

		List<TimetableAction> timetable = template.exchange(
				STAG_URL + "rozvrhy/getRozvrhByStudent?osCislo=" + students.get(0).getUserNumbers().get(0)
						+ "&outputFormat=JSON",
				HttpMethod.GET, entity, new ParameterizedTypeReference<List<TimetableAction>>() {
				}).getBody();
		if (timetable != null) {
			loggingLogger.info("User " + username + " authorized successfully");
			byte[] credentialsByte = Base64.getDecoder().decode(auth.substring(auth.indexOf(" ") + 1));
			String[] credentials = new String(credentialsByte).split(":");
			return userService.getUserDTOByUsernameAndPassword(credentials[0], credentials[1]);
		} else {
			loggingLogger.info("User " + username + " was not authorized");
			return null;
		}
	}

	private UserDTO authorizeTeacher(String auth, String ucitIdNo, HttpEntity<String> entity) {
		// Just to authorize user
		List<TimetableAction> timetable = template
				.exchange(STAG_URL + "rozvrhy/getRozvrhByStudent?osCislo=I1&outputFormat=JSON", HttpMethod.GET, entity,
						new ParameterizedTypeReference<List<TimetableAction>>() {
						})
				.getBody();
		if (timetable != null) {
			loggingLogger.info("User authorized successfully");
			byte[] credentialsByte = Base64.getDecoder().decode(auth.substring(auth.indexOf(" ") + 1));
			String[] credentials = new String(credentialsByte).split(":");
			return userService.getUserDTOByUsernameAndPassword(credentials[0], credentials[1]);
		} else {
			loggingLogger.info("User was not authorized");
			return null;
		}
	}

}
