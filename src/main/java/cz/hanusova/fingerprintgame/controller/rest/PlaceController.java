package cz.hanusova.fingerprintgame.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.hanusova.fingerprintgame.model.Activity;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.service.PlaceService;

@RestController
@RequestMapping("/android/1.0/place")
public class PlaceController {

	@Autowired
	private PlaceService placeService;

	@RequestMapping("/addActivity")
	public void startActivity(@RequestParam("username") String username, @RequestBody Place place) {
		// TODO: uzivatele ziskat z autentizace
		Activity activity = place.getPlaceType().getActivity();
		placeService.startActivity(username, place, activity);
	}
}
