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

}