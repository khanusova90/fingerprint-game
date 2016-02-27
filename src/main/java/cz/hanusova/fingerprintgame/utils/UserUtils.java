package cz.hanusova.fingerprintgame.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public final class UserUtils {

	private UserUtils() {
	}

	/**
	 * @return Uzivatelske jmeno aktualne prihlaseneho uzivatele
	 */
	public static String getActualUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	/**
	 * @return Aktualne prihlaseny uzivatel
	 */
	public static User getActualUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
}
