
/**
 * 
 */
package cz.hanusova.fingerprintgame.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author khanusova
 *
 */
public class StagUser implements Serializable {

	@JsonProperty("osCislo")
	private List<String> userNumbers;

	public List<String> getUserNumbers() {
		return userNumbers;
	}

	public void setUserNumbers(List<String> userNumbers) {
		this.userNumbers = userNumbers;
	}

}
