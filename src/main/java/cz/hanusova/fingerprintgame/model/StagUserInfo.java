/**
 * 
 */
package cz.hanusova.fingerprintgame.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author khanusova
 *
 */
public class StagUserInfo {

	@JsonProperty("stagUserInfo")
	private List<StagUser> users;

	/**
	 * @return the users
	 */
	public List<StagUser> getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(List<StagUser> users) {
		this.users = users;
	}

}
