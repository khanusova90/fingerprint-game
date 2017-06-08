/**
 * 
 */
package cz.hanusova.fingerprintgame.dto;

import java.util.ArrayList;
import java.util.List;

import cz.hanusova.fingerprintgame.model.Character;
import cz.hanusova.fingerprintgame.model.Inventory;
import cz.hanusova.fingerprintgame.model.Item;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.Role;
import cz.hanusova.fingerprintgame.model.UserActivity;

/**
 * @author khanusova
 *
 */
public class UserDTO {

	private Long idUser;
	private String username;
	private String password;
	private String stagname;
	private Character character;
	private List<Inventory> inventory = new ArrayList<>();
	private List<Role> roles = new ArrayList<>();
	private List<UserActivity> activities = new ArrayList<>();
	private List<Place> places = new ArrayList<>();
	private List<Item> items = new ArrayList<>();

	/*
	 * Information to send to client
	 */
	private int placeProgress;
	private int level;
	private int levelProgress;

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

	/**
	 * @return the places
	 */
	public List<Place> getPlaces() {
		return places;
	}

	/**
	 * @param places
	 *            the places to set
	 */
	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

	/**
	 * @return the placeProgress
	 */
	public int getPlaceProgress() {
		return placeProgress;
	}

	/**
	 * @param placeProgress
	 *            the placeProgress to set
	 */
	public void setPlaceProgress(int placeProgress) {
		this.placeProgress = placeProgress;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the levelProgress
	 */
	public int getLevelProgress() {
		return levelProgress;
	}

	/**
	 * @param levelProgress
	 *            the levelProgress to set
	 */
	public void setLevelProgress(int levelProgress) {
		this.levelProgress = levelProgress;
	}

}
