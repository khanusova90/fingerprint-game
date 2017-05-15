/**
 * 
 */
package cz.hanusova.fingerprintgame.service;

import cz.hanusova.fingerprintgame.dto.UserDTO;

/**
 * @author khanusova
 *
 */
public interface LoginService {

	/**
	 * Tries to sign in user via IS STAG <br />
	 * If user signs in STAG successfully, creates a new one in DB or updates
	 * his password in case that user with this username already exists.
	 * 
	 * @param auth
	 *            Auth header from client (Basic auth)
	 * @param username
	 * @return {@link UserDTO} or <code>null</code> in case that user was not
	 *         logged in STAG
	 */
	public UserDTO loginUser(String auth, String username);

}
