package cz.hanusova.fingerprintgame.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	private String stagname;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_CHARACTER")
	private Character character;

	@JoinColumn(name = "ID_APP_USER")
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Inventory> inventory = new ArrayList<>();

	@ElementCollection(targetClass = Role.class)
	@CollectionTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "ID_APP_USER") })
	@Column(name = "ROLE")
	@Enumerated(EnumType.STRING)
	private List<Role> roles = new ArrayList<>();

	@JoinColumn(name = "ID_APP_USER")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<UserActivity> activities = new ArrayList<>();

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
	public String getStagname() {
		return stagname;
	}

	/**
	 * @param stagname
	 *            the stagname to set
	 */
	public void setStagname(String stagName) {
		this.stagname = stagName;
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
	public List<Inventory> getInventory() {
		return inventory;
	}

	/**
	 * @param inventory
	 *            the inventory to set
	 */
	public void setInventory(List<Inventory> inventory) {
		this.inventory = inventory;
	}

	/**
	 * @return the activities
	 */
	public List<UserActivity> getActivities() {
		return activities;
	}

	/**
	 * @param activities
	 *            the activities to set
	 */
	public void setActivities(List<UserActivity> activities) {
		this.activities = activities;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
