
/**
 * 
 */
package cz.hanusova.fingerprintgame.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User from STAG IS
 * 
 * @author khanusova
 *
 */
public class StagUser implements Serializable {

	@JsonProperty("osCislo")
	private List<String> userNumbers;
	private String userName;
	private String role;
	private String ucitIdNo;

	public List<String> getUserNumbers() {
		return userNumbers;
	}

	public void setUserNumbers(List<String> userNumbers) {
		this.userNumbers = userNumbers;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the ucitIdNo
	 */
	public String getUcitIdNo() {
		return ucitIdNo;
	}

	/**
	 * @param ucitIdNo
	 *            the ucitIdNo to set
	 */
	public void setUcitIdNo(String ucitIdNo) {
		this.ucitIdNo = ucitIdNo;
	}

}
