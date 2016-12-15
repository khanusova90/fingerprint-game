package cz.hanusova.fingerprintgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Place;
import cz.hanusova.fingerprintgame.model.UserActivity;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

	/**
	 * 
	 * 
	 * @param place
	 * @param user
	 * @return
	 */
	public UserActivity getFirstByPlaceAndUserAndStopTimeIsNull(Place place, AppUser user);

}
