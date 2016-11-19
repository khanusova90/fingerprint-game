package cz.hanusova.fingerprintgame.model;

import cz.hanusova.fingerprintgame.utils.EnumTranslator;

@Deprecated
public enum MaterialEnum {

	GOLD("GOLD"), FOOD("FOOD"), WOOD("WOOD"), STONE("STONE"), WORKER("WORKER");

	private String name;

	private MaterialEnum(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public String getKey() {
		return EnumTranslator.getMessageKey(this);
	}

}
