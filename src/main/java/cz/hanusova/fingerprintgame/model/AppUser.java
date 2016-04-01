package cz.hanusova.fingerprintgame.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Trida reprezentujici uzivatele
 * 
 * @author Katerina Hanusova
 *
 */
@Entity
@Table(name = "APP_USER")
public class AppUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USER")
	private Long idUser;
	
	@Column(name="USERNAME")
	private String username;

	@Column(name = "STAGNAME", nullable = true)
	private String stagname;
	
	@Column(name="PASSWORD")
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Inventory> inventory = new HashSet<>();

	@ElementCollection
	@CollectionTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "ID_USER") })
	@Column(name = "ROLE")
	private Set<String> userRoles = new HashSet<>();

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

	public Set<Inventory> getInventory() {
		return inventory;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStagname() {
		return stagname;
	}

	public void setStagname(String stagname) {
		this.stagname = stagname;
	}

	public Set<String> getUserRoles() {
		return userRoles;
	}
}
