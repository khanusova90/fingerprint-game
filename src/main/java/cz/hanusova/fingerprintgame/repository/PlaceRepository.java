package cz.hanusova.fingerprintgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.hanusova.fingerprintgame.model.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {

	public Place findFirstByCode(String code);

}
