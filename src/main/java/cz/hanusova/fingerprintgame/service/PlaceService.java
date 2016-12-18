package cz.hanusova.fingerprintgame.service;

import java.util.List;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.UserActivity;

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
	 * @return List of user's actual activities
	 */
	public List<UserActivity> startActivity(AppUser user, Place place, Float workerAmount);

}
