package cz.hanusova.fingerprintgame.service;

import java.util.Set;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Inventory;

public interface UserService {

	/**
	 * Ulozi noveho uzivatele
	 * 
	 * @param user
	 *            Instance tridy {@link AppUser} s vyplnenymi hodnotami
	 * @return <code>false</code>, pokud uz uzivatel se zadanym uzivatelskym
	 *         jmenem v DB existuje, jinak vraci <code>true</code>
	 */
	public Boolean saveUser(AppUser user);

	/**
	 * Vyhleda aktualniho uzivatele a jeho inventar
	 * 
	 * @return Seznam {@link Inventory} aktuaniho uzivatele
	 */
	public Set<Inventory> getUserInventory();

}