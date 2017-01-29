/**
 * 
 */
package cz.hanusova.fingerprintgame.service;

import java.math.BigDecimal;

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
	 * Checks actually running activities of all users and updates their
	 * inventory
	 */
	void checkRunningActivities();

}