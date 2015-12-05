package cz.hanusova.fingerprintgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdministrationController {

	@RequestMapping(value = "administration")
	public void getRequest() {
		System.out.println("CONTROLLER");
	}

}
