package cz.hanusova.fingerprintgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cz.hanusova.fingerprintgame.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {

	public AppUser findByUsername(String username);

	// @Query("select u from AppUser u left join fetch u.userRoles ur where
	// u.username = :username")
	@Query("from AppUser u left join fetch u.roles r where u.username = :username")
	public AppUser findByUsernameFetch(@Param("username") String username);

}
