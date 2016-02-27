package cz.hanusova.fingerprintgame.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.hanusova.fingerprintgame.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	public Set<Inventory> findInventoryByUser_Username(String username);

}
