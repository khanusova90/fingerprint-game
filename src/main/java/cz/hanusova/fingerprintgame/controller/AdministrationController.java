package cz.hanusova.fingerprintgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cz.hanusova.fingerprintgame.model.Resource;
import cz.hanusova.fingerprintgame.service.ResourceService;

@Controller
@RequestMapping(value = "/administration")
public class AdministrationController {
	
	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "")
	public String getRequest(Model model) {
		model.addAttribute("floor", 1);
		model.addAttribute("imgsrc", "/resources/img/J1NP.jpg");

		return "administration";
	}

	@RequestMapping("/maps")
	public String getMaps(@RequestParam("floor") String floor, Model model) {
		model.addAttribute("floor", floor);
		String imgSrc = "/resources/img/J" + floor + "NP.jpg";
		model.addAttribute("imgsrc", imgSrc);

		return "administration";
	}
	
	@RequestMapping("/save")
	public String saveCircles(@ModelAttribute("floor") String floor, @RequestBody Resource[] resources){
		resourceService.saveResources(resources);
		return "administration";
	}

}
