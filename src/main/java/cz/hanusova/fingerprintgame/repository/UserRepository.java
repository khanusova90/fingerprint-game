package cz.hanusova.fingerprintgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cz.hanusova.fingerprintgame.domain.AppUser;

@Transactional
public interface UserRepository extends JpaRepository<AppUser, Long> {

	public AppUser findByUsername(String username);

}
