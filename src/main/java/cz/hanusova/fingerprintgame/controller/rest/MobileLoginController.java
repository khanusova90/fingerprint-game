package cz.hanusova.fingerprintgame.controller.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.hanusova.fingerprintgame.dto.UserDTO;
import cz.hanusova.fingerprintgame.service.UserService;

@RestController
@RequestMapping("/android/1.0")
public class MobileLoginController {

	private static final Log logger = LogFactory.getLog(MobileLoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.POST)
	public UserDTO getUser(@RequestParam("username") String username) {
		logger.info("Getting user from mobile app: " + username);
		return userService.getUserDTOByUsername(username);
	}

}
