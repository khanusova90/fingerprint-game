package cz.hanusova.fingerprintgame.model;

import cz.hanusova.fingerprintgame.utils.EnumTranslator;

public enum ActivityEnum {

	MINE, CHANGE, BUILD, BUY;

	public String getKey() {
		return EnumTranslator.getMessageKey(this);
	}

}
