package cz.hanusova.fingerprintgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping(value={"/","/login"})
	public String getRequest(Model model) {
		model.addAttribute("loginError", false);
		model.addAttribute("logout", false);
		model.addAttribute("denied", false);

		return "login";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

	@RequestMapping("/login-logout")
	public String logout(Model model) {
		model.addAttribute("logout", true);
		return "login";
	}

	@RequestMapping("/login-denied")
	public String deny(Model model) {
		model.addAttribute("denied", true);
		return "login";
	}

}
