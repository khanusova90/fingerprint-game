package cz.hanusova.fingerprintgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Inventory;
import cz.hanusova.fingerprintgame.model.Material;

public interface UserRepository extends JpaRepository<AppUser, Long> {

	public AppUser findByUsername(String username);

	@Query("from AppUser u left join fetch u.roles r where u.username = :username")
	public AppUser findByUsernameFetch(@Param("username") String username);

	public Inventory findFirstInventoryByUserAndInventory_material(Material material, AppUser user);

}
