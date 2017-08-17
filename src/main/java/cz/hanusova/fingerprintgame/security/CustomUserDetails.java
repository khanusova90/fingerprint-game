package cz.hanusova.fingerprintgame.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cz.hanusova.fingerprintgame.model.AppUser;
import cz.hanusova.fingerprintgame.model.Role;

/**
 * User details for Spring security
 * 
 * @author khanusova
 *
 */
class CustomUserDetails implements UserDetails {

	private List<GrantedAuthority> authorities;
	private String password;
	private String username;

	public CustomUserDetails(AppUser user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.authorities = getUserAuthorities(user);
	}

	private List<GrantedAuthority> getUserAuthorities(AppUser user) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));
		}
		return grantedAuthorities;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

}
