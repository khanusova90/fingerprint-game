/**
 * 
 */
package cz.hanusova.fingerprintgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cz.hanusova.fingerprintgame.model.Role;

/**
 * @author khanusova
 *
 */
public interface RoleRepository extends JpaRepository<Long, Role> {

	/**
	 * @return {@link Role} with name <b>ROLE_USER</b>
	 */
	@Query("from role r where r.name = ROLE_USER")
	public Role getUserRole();

}
