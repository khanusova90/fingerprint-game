package cz.hanusova.fingerprintgame.controller.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.hanusova.fingerprintgame.dto.UserDTO;
import cz.hanusova.fingerprintgame.service.LoginService;

@RestController
@RequestMapping("/android/1.0")
public class MobileLoginController {

	private static final Log logger = LogFactory.getLog(MobileLoginController.class);
	private static final Log loggingLogger = LogFactory.getLog("logging");

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Object> getUser(@RequestParam("username") String username,
			@RequestHeader("authorization") String auth) {
		logger.info("Getting user from mobile app: " + username);
		loggingLogger.info("Getting user from mobile app: " + username);

		UserDTO user = loginService.loginUser(auth, username);
		if (user == null) {
			loggingLogger.warn(username + " UNAUTHORIZED");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			loggingLogger.info(username + " logged in successfully");
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		}
	}

}
