package cz.hanusova.fingerprintgame.service;

import cz.hanusova.fingerprintgame.model.AppUser;
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
	 * Starts new activity and updates user's amount of workers in inventory
	 * 
	 * @param user
	 *            {@link AppUser} who is starting new activity
	 * @param place
	 *            {@link Place} where new activity is
	 * @param workerAmount
	 *            amount of workers for activity
	 * @return updated {@link AppUser}
	 */
	public AppUser startActivity(AppUser user, Place place, Float workerAmount);

	/**
	 * Checks if given place is already in user's list. If not, adds it there
	 * 
	 * @param place
	 *            {@link Place} actually found by user
	 */
	public void checkUserPlace(Place place);

}
