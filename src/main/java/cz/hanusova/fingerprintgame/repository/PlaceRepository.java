package cz.hanusova.fingerprintgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.hanusova.fingerprintgame.model.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {

	public Place findFirstByCode(String code);

	// @Query("select p from Place p left join fetch p.resources r where p.code
	// = :code")
	// public Place findFirstByCodeFetch(@Param("code")String code);

}
