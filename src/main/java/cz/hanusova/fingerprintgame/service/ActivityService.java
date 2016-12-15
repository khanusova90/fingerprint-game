/**
 * 
 */
package cz.hanusova.fingerprintgame.service;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.UserActivity;

/**
 * @author khanusova
 *
 */
public interface ActivityService {

	/**
	 * Starts new {@link UserActivity} with given amount of workers from user's
	 * inventory
	 * 
	 * @param place
	 * @param workerAmount
	 * @param user
	 */
	public void startNewActivity(Place place, Float workerAmount, AppUser user);

	/**
	 * Removes {@link UserActivity} from {@link AppUser} and deletes it from
	 * database
	 * 
	 * @param activity
	 * @param user
	 */
	public void removeActivity(UserActivity activity, AppUser user);

	/**
	 * Updates {@link UserActivity}. Changes its workersAmount and sets start
	 * date to actual time. Updates also user's inventory
	 * 
	 * @param activity
	 * @param workersAmount
	 * @param user
	 */
	public void changeActivity(UserActivity activity, Float workersAmount, AppUser user);

}