/**
 * 
 */
package cz.hanusova.fingerprintgame.service;

import cz.hanusova.fingerprintgame.model.AppUser;

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
	 */
	public void updateWorkerAmount(Float workerAmount, AppUser user);

	/**
	 * @param amount
	 *            amount of stone that user used. Pass negative parameter value
	 *            to increase stone amount in inventory
	 * @param user
	 */
	public void updateStoneAmount(Float amount, AppUser user);

	/**
	 * @param amount
	 *            amount of wood that user used. Pass negative parameter value
	 *            to increase wood amount in inventory
	 * @param user
	 */
	public void updateWoodAmount(Float amount, AppUser user);

	/**
	 * @param amount
	 *            amount of food that user used. Pass negative parameter value
	 *            to increase food amount in inventory
	 * @param user
	 */
	public void updateFoodAmount(Float amount, AppUser user);

	/**
	 * @param amount
	 *            amount of gold that user used. Pass negative parameter value
	 *            to increase gold amount in inventory
	 * @param user
	 */
	void updateGoldAmount(Float amount, AppUser user);

}