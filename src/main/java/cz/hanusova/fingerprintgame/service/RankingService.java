/**
 * 
 */
package cz.hanusova.fingerprintgame.service;

import java.util.List;

import cz.hanusova.fingerprintgame.dto.RankingDTO;

/**
 * @author khanusova
 *
 */
public interface RankingService {

	/**
	 * @return List of all {@link RankingDTO} for all users that have some XP
	 */
	List<RankingDTO> getRankings();

}
