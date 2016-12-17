package cz.hanusova.fingerprintgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.hanusova.fingerprintgame.model.UserActivity;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {

}
