package cz.hanusova.fingerprintgame.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.repository.UserRepository;

/**
 * Service class for loading users for Spring security
 * 
 * @author khanusova
 *
 */
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private static final Log logger = LogFactory.getLog(CustomUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Loading user: " + username);
		AppUser user = userRepository.findByUsername(username);
		return new CustomUserDetails(user);
	}
}
