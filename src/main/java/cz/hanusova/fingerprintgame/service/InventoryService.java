/**
 * 
 */
package cz.hanusova.fingerprintgame.service;

import java.math.BigDecimal;

import cz.hanusova.fingerprintgame.model.AppUser;
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

	// /**
	// * Adds mined material
	// *
	// * @param place
	// * {@link Place} where mining takes place
	// * @param user
	// * {@link AppUser} who is mining
	// * @param workers
	// * amount of workers mining
	// */
	// public void addMining(Place place, AppUser user, float workers);

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
	 * Starts mining with given amount of workers only in case that user has
	 * enough food to feed them
	 * 
	 * @param place
	 * @param user
	 * @param workers
	 */
	void mine(Place place, AppUser user, float workers);

	// /**
	// * Subtracts food for actually mining workers
	// *
	// * @param workers
	// * number of workers mining
	// * @param user
	// * {@link AppUser}
	// * @return <code>true</code> if user has enough food for workers otherwise
	// * returns <code>false</code>
	// */
	// boolean feedWorkers(float workers, AppUser user);

	// /**
	// * Finds out if user has enough food to feed workers
	// *
	// * @param workers
	// * float value of workers amount
	// * @param user
	// * {@link AppUser}
	// * @return <code>true</code> if user has enough food for given amount of
	// * workers
	// */
	// boolean hasEnoughFood(float workers, AppUser user);

}