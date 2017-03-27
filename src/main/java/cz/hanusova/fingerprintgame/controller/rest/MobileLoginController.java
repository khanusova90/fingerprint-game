package cz.hanusova.fingerprintgame.controller.rest;

import java.util.Base64;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import cz.hanusova.fingerprintgame.model.StagUser;
import cz.hanusova.fingerprintgame.model.TimetableAction;
import cz.hanusova.fingerprintgame.service.UserService;

@RestController
@RequestMapping("/android/1.0")
public class MobileLoginController {

	private static final Log logger = LogFactory.getLog(MobileLoginController.class);

	private static final String STAG_URL = "https://stagws.uhk.cz/ws/services/rest/";

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Object> getUser(@RequestParam("username") String username,
			@RequestHeader("authorization") String auth) {
		logger.info("Getting user from mobile app: " + username);
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
					// return
					// userService.getUserDTOByUsernameAndPassword(credentials[0],
					// credentials[1]);
					return new ResponseEntity<Object>(
							userService.getUserDTOByUsernameAndPassword(credentials[0], credentials[1]), HttpStatus.OK);
				}
			}
		} catch (HttpClientErrorException e) {
			logger.warn("Returned 401 from STAG");
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

}
