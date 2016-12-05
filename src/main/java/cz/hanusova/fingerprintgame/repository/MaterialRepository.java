/**
 * 
 */
package cz.hanusova.fingerprintgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cz.hanusova.fingerprintgame.model.Material;

/**
 * @author khanusova
 *
 */
public interface MaterialRepository extends JpaRepository<Material, Long> {

	@Query("from Material m where m.name = 'GOLD'")
	public Material findGold();

	@Query("from Material m where m.name = 'FOOD'")
	public Material findFood();

	@Query("from Material m where m.name = 'WOOD'")
	public Material findWood();

	@Query("from Material m where m.name = 'STONE'")
	public Material findStone();

	@Query("from Material m where m.name = 'WORKER'")
	public Material findWorker();

}
