package cz.hanusova.fingerprintgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/administration")
public class AdministrationController {

	@RequestMapping(value = "/")
	public ModelAndView getRequest() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("administration");
		mv.addObject("header", "Administrace hry");

		return mv;
	}

	@RequestMapping(value = "/test")
	public String getRequesttest() {
		System.out.println("test");

		return "administration";
	}

}
