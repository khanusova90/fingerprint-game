package cz.hanusova.fingerprintgame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.repository.PlaceRepository;
import cz.hanusova.fingerprintgame.service.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService{
	
	@Autowired
	private PlaceRepository placeRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Place getPlaceByCode(String code){
		return placeRepository.findFirstByCodeFetch(code);
	}

}
