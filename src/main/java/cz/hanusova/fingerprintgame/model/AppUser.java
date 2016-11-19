package cz.hanusova.fingerprintgame.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	@Column(name = "ID_APP_USER")
	private Long idUser;

	@NotNull
	private String username;

	@NotNull
	private String password;

	private String stagName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_CHARACTER")
	private Character character;

	// @Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name = "ID_USER")
	// private Long idUser;
	//
	// @Column(name = "USERNAME")
	// private String username;
	//
	// @Column(name = "STAGNAME", nullable = true)
	// private String stagname;
	//
	// @Column(name = "PASSWORD")
	// private String password;
	//
	@JoinColumn(name = "ID_USER")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Inventory> inventory = new HashSet<>();
	//
	// @ElementCollection
	// @CollectionTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name =
	// "ID_USER") })
	// @Column(name = "ROLE")
	// private Set<String> userRoles = new HashSet<>();
	//
	@JoinColumn(name = "ID_USER")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<UserActivity> activities = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "ID_APP_USER") }, inverseJoinColumns = {
			@JoinColumn(name = "ID_ROLE") })
	private Set<Role> roles;

	/*
	 * Getters and setters
	 */
	/**
	 * @return the idUser
	 */
	public Long getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser
	 *            the idUser to set
	 */
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the stagName
	 */
	public String getStagName() {
		return stagName;
	}

	/**
	 * @param stagName
	 *            the stagName to set
	 */
	public void setStagName(String stagName) {
		this.stagName = stagName;
	}

	/**
	 * @return the character
	 */
	public Character getCharacter() {
		return character;
	}

	/**
	 * @param character
	 *            the character to set
	 */
	public void setCharacter(Character character) {
		this.character = character;
	}

	/**
	 * @return the inventory
	 */
	public Set<Inventory> getInventory() {
		return inventory;
	}

	/**
	 * @param inventory
	 *            the inventory to set
	 */
	public void setInventory(Set<Inventory> inventory) {
		this.inventory = inventory;
	}

	/**
	 * @return the activities
	 */
	public Set<UserActivity> getActivities() {
		return activities;
	}

	/**
	 * @param activities
	 *            the activities to set
	 */
	public void setActivities(Set<UserActivity> activities) {
		this.activities = activities;
	}

	/**
	 * @return the role
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	//
	// public Long getIdUser() {
	// return idUser;
	// }
	//
	// public void setIdUser(Long idUser) {
	// this.idUser = idUser;
	// }
	//
	// public String getUsername() {
	// return username;
	// }
	//
	// public void setUsername(String username) {
	// this.username = username;
	// }
	//
	// public Set<Inventory> getInventory() {
	// return inventory;
	// }
	//
	// public String getPassword() {
	// return password;
	// }
	//
	// public void setPassword(String password) {
	// this.password = password;
	// }
	//
	// public String getStagname() {
	// return stagname;
	// }
	//
	// public void setStagname(String stagname) {
	// this.stagname = stagname;
	// }
	//
	// public Set<String> getUserRoles() {
	// return userRoles;
	// }
	//
	// public Set<UserActivity> getActivities() {
	// return activities;
	// }
	//
	// public void setActivities(Set<UserActivity> activities) {
	// this.activities = activities;
	// }
	//
	// public Character getCharacter() {
	// return character;
	// }
	//
	// public void setCharacter(Character character) {
	// this.character = character;
	// }
}
