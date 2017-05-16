/**
 * 
 */
package cz.hanusova.fingerprintgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author khanusova
 *
 */
@Controller
public class PolicyController {

	@RequestMapping("/policy")
	public String showPrivacyPolicy() {
		return "policy";
	}

}
