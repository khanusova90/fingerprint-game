/**
 * 
 */
package cz.hanusova.fingerprintgame.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.hanusova.fingerprintgame.dto.RankingDTO;
import cz.hanusova.fingerprintgame.service.RankingService;

/**
 * Controller for handling rankings
 * 
 * @author khanusova
 *
 */
@RestController
@RequestMapping("/android/1.0/ranking")
public class RankingController {

	@Autowired
	private RankingService rankingService;

	@RequestMapping("/get")
	public List<RankingDTO> getRanking() {
		return rankingService.getRankings();
	}

}
