/**
 * 
 */
package cz.hanusova.fingerprintgame.dto;

/**
 * DTO class for sending ranking info
 * 
 * @author khanusova
 *
 */
public class RankingDTO {

	private String username;
	private int xp;

	/**
	 * 
	 */
	public RankingDTO(String username, int xp) {
		this.username = username;
		this.xp = xp;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the xp
	 */
	public int getXp() {
		return xp;
	}

	/**
	 * @param xp
	 *            the xp to set
	 */
	public void setXp(int xp) {
		this.xp = xp;
	}

}
