package cz.hanusova.fingerprintgame.service;

import cz.hanusova.fingerprintgame.model.Activity;
import cz.hanusova.fingerprintgame.model.Place;

public interface PlaceService {

	/**
	 * Finds place by given code saved in QR code
	 * 
	 * @param code
	 *            String code from URL
	 * @return {@link Place} with given code
	 */
	public Place getPlaceByCode(String code);

	/**
	 * Starts new activity for user with given username
	 * 
	 * @param username
	 * @param place
	 * @param activity
	 */
	@Deprecated
	public void startActivity(String username, Place place, Activity activity);

}
