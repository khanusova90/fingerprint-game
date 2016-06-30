package cz.hanusova.fingerprintgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.hanusova.fingerprintgame.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
