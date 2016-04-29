package cz.hanusova.fingerprintgame.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.service.PlaceService;

@RestController
@RequestMapping("/android/qr")
public class QrController {
	
	private static final Log logger = LogFactory.getLog(QrController.class);
	
	@Autowired
	private PlaceService placeService;
	
	@RequestMapping(value = "/{code}", produces = "application/json")
	public Place getPlaceByCode(@PathVariable("code") String code){
		logger.info("Getting place with code " + code);
		return placeService.getPlaceByCode(code);
	}

}
