package cz.hanusova.fingerprintgame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import cz.hanusova.fingerprintgame.model.Inventory;
import cz.hanusova.fingerprintgame.service.UserService;

//@Controller
//@RequestMapping(value = { "/overview", "/signup" })
public class OverviewController {

	@Autowired
	private UserService userService;

	// @RequestMapping("")
	public String getRequest(Model model) {
		addUserInventory(model);
		model.addAttribute("floor", 1);
		model.addAttribute("imgsrc", "/resources/img/J1NP.jpg");

		return "overview";
	}

	// @RequestMapping("/maps")
	public String getMaps(@RequestParam("floor") String floor, Model model) {
		addUserInventory(model);
		model.addAttribute("floor", floor);
		String imgSrc = "/resources/img/J" + floor + "NP.jpg";
		model.addAttribute("imgsrc", imgSrc);

		return "overview";
	}

	private void addUserInventory(Model model) {
		List<Inventory> inventory = userService.getUserInventory();
		model.addAttribute("inventory", inventory);
	}

}
