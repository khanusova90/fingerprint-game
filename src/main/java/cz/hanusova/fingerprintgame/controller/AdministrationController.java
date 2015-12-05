package cz.hanusova.fingerprintgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/administration")
public class AdministrationController {

	@RequestMapping(value = "/")
	public String getRequest() {
		System.out.println("CONTROLLER");

		return "administration";
	}

	@RequestMapping(value = "/test")
	public String getRequesttest() {
		System.out.println("test");

		return "administration";
	}

}
