package cz.hanusova.fingerprintgame.service;

import cz.hanusova.fingerprintgame.model.Resource;

public interface ResourceService {

	/**
	 * Ulozi vsechny zadane zdroje do databaze
	 * 
	 * @param resources Pole objektu typu {@link Resource}
	 */
	public void saveResources(Resource[] resources);

}