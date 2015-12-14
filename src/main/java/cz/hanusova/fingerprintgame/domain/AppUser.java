package cz.hanusova.fingerprintgame.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Trida reprezentujici uzivatele
 * 
 * @author Katerina Hanusova
 *
 */
@Entity
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUser;
	private String username;
	private String stagLogin;
	private String password;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<Inventory> inventory;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Inventory> getInventory() {
		return inventory;
	}

	public void setInventory(List<Inventory> inventory) {
		this.inventory = inventory;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStagLogin() {
		return stagLogin;
	}

	public void setStagLogin(String stagLogin) {
		this.stagLogin = stagLogin;
	}

}
