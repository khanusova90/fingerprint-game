package cz.hanusova.fingerprintgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.hanusova.fingerprintgame.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long>{

}
