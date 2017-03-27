package cz.hanusova.fingerprintgame.service;

import java.util.List;

import cz.hanusova.fingerprintgame.dto.UserDTO;
import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Inventory;

public interface UserService {

	/**
	 * Ulozi noveho uzivatele
	 * 
	 * @param user
	 *            Instance tridy {@link AppUser} s vyplnenymi hodnotami
	 * @return <code>false</code>, pokud uz uzivatel se zadanym uzivatelskym
	 *         jmenem v DB existuje, jinak vraci <code>true</code>
	 */
	public Boolean saveUser(AppUser user);

	/**
	 * Vyhleda aktualniho uzivatele a jeho inventar
	 * 
	 * @return Seznam {@link Inventory} aktuaniho uzivatele
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