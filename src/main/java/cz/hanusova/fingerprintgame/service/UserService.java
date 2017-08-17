package cz.hanusova.fingerprintgame.service;

import java.util.List;

import cz.hanusova.fingerprintgame.dto.UserDTO;
import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Inventory;

public interface UserService {

	/**
	 * Saves new user to DB
	 * 
	 * @param user
	 *            Instance of {@link AppUser} with attributes filled
	 * @return <code>false</code> if and only if user with this username already
	 *         exists in DB. Otherwise returns <code>true</code>
	 */
	public Boolean saveUser(AppUser user);

	/**
	 * @return {@link Inventory} list of actual user
	 */
	public List<Inventory> getUserInventory();

	/**
	 * Finds user by given username
	 * 
	 * @param username
	 * @return {@link AppUser}
	 */
	public AppUser getUserByName(String username);

	/**
	 * Finds user by given username with all collections initialized. <br />
	 * If no {@link AppUser} with this username exists, creates a new one.
	 * 
	 * @param username
	 * @return {@link UserDTO} filled with all information (including
	 *         collections) about user
	 */
	public UserDTO getUserDTOByUsername(String username);

	/**
	 * @return {@link AppUser} who is actually signed in
	 */
	public AppUser getActualUser();

	/**
	 * @param username
	 * @param password
	 * @return
	 */
	UserDTO getUserDTOByUsernameAndPassword(String username, String password);

}