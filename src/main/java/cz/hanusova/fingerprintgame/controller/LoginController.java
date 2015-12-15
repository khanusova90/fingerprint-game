package cz.hanusova.fingerprintgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping("/")
	public String getLoginPage() {
		return "login";
	}

	@RequestMapping("/login")
	public String getRequest() {
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

	//TODO extract to different controller
	@RequestMapping("/overview")
	public String overview() {
		return "overview";
	}

}
