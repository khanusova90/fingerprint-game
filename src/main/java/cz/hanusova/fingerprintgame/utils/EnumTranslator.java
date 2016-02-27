package cz.hanusova.fingerprintgame.utils;

/**
 * Trida pro prekladani ciselniku
 * 
 * @author Katerina Hanusova
 *
 */
public final class EnumTranslator {

	public static String getMessageKey(Enum<?> e) {
		return e.getClass().getSimpleName() + "." + e.name();
	}

}
