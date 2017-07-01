/**
 * 
 */
package cz.hanusova.fingerprintgame.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.hanusova.fingerprintgame.dto.RankingDTO;
import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.repository.UserRepository;
import cz.hanusova.fingerprintgame.service.RankingService;

/**
 * @author khanusova
 *
 */
@Service
public class RankingServiceImpl implements RankingService {

	private UserRepository userRepository;

	@Autowired
	public RankingServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<RankingDTO> getRankings() {
		List<AppUser> allUsers = userRepository.findAll();
		allUsers = allUsers.stream().filter(u -> u.getCharacter().getXp() != 0).collect(Collectors.toList());
		List<RankingDTO> rankings = new ArrayList<>();
		for (AppUser user : allUsers) {
			RankingDTO ranking = new RankingDTO(user.getUsername(), user.getCharacter().getXp());
			rankings.add(ranking);
		}
		return rankings;
	}

}
