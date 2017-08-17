/**
 * 
 */
package cz.hanusova.fingerprintgame.service;

import java.math.BigDecimal;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Material;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.UserActivity;

/**
 * @author khanusova
 *
 */
public interface InventoryService {

	/**
	 * Subtracts given worker amount from user's repository
	 * 
	 * @param workerAmount
	 *            amount of workers that user has started to use. If user has
	 *            stopped some activity, this parameter should be negative so as
	 *            amount is added
	 * @param user
	 * @return actual worker amount
	 */
	public BigDecimal updateWorkerAmount(Float workerAmount, AppUser user);

	/**
	 * @param amount
	 *            amount of stone that user used. Pass negative parameter value
	 *            to increase stone amount in inventory
	 * @param user
	 * @return actual stone amount
	 */
	public BigDecimal updateStoneAmount(Float amount, AppUser user);

	/**
	 * @param amount
	 *            amount of wood that user used. Pass negative parameter value
	 *            to increase wood amount in inventory
	 * @param user
	 * @return actual wood amount
	 */
	public BigDecimal updateWoodAmount(Float amount, AppUser user);

	/**
	 * @param amount
	 *            amount of food that user used. Pass negative parameter value
	 *            to increase food amount in inventory
	 * @param user
	 * @return actual food amount
	 */
	public BigDecimal updateFoodAmount(Float amount, AppUser user);

	/**
	 * @param amount
	 *            amount of gold that user used. Pass negative parameter value
	 *            to increase gold amount in inventory
	 * @param user
	 * @return actual gold amount
	 */
	public BigDecimal updateGoldAmount(Float amount, AppUser user);

	/**
	 * Subtracts material as rent for houses that user has built
	 * 
	 * @param activity
	 *            currently running building {@link UserActivity}
	 * @param user
	 *            {@link AppUser}
	 */
	public void payRent(UserActivity activity, AppUser user);

	/**
	 * Starts mining with given amount of workers and subtracts food for them
	 * 
	 * @param place
	 * @param user
	 * @param workers
	 */
	void mine(Place place, AppUser user, float workers);

	/**
	 * Finds out if user has enough food to feed workers
	 *
	 * @param workers
	 *            float value of workers amount
	 * @param user
	 *            {@link AppUser}
	 * @return <code>true</code> if user has enough food for given amount of
	 *         workers
	 */
	boolean hasEnoughFood(float workers, AppUser user);

	/**
	 * Finds out if user has enough gold to pay rent for his workers
	 * 
	 * @param workers
	 *            float value of workers amount
	 * @param user
	 * @return <code>true</code> if user has enough gold to pay rent
	 */
	boolean hasEnoughGold(float workers, AppUser user);

	/**
	 * Stops activity for building houses and subtracts workers from user's
	 * inventory
	 * 
	 * @param activity
	 *            {@link UserActivity} for building houses
	 * @param user
	 */
	void stopBuilding(UserActivity activity, AppUser user);

	/**
	 * Updates material amount for user
	 * 
	 * @param material
	 *            {@link Material} that should be updated
	 * @param user
	 *            {@link AppUser}
	 * @param workers
	 *            amount of workers that mine this material
	 */
	void updateMaterial(Material material, AppUser user, float workers);

}