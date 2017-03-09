/**
 * 
 */
package cz.hanusova.fingerprintgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.hanusova.fingerprintgame.model.ItemType;

/**
 * @author khanusova
 *
 */
public interface ItemTypeRepository extends JpaRepository<ItemType, Long> {

}
