package cz.hanusova.fingerprintgame.model;

import cz.hanusova.fingerprintgame.utils.EnumTranslator;

public enum ActivityEnum {

	MINE;

	// private String name;
	//
	// private ActivityEnum(String name) {
	// this.name = name;
	// }

	// @Override
	// public String toString() {
	// return name;
	// }

	public String getKey() {
		return EnumTranslator.getMessageKey(this);
	}

}
