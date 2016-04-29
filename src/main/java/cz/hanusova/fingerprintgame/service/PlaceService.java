package cz.hanusova.fingerprintgame.service;

import cz.hanusova.fingerprintgame.model.Place;

public interface PlaceService {

	/**
	 * Finds place by given code saved in QR code
	 * 
	 * @param code String code from URL
	 * @return {@link Place} with given code
	 */
	public Place getPlaceByCode(String code);

}
