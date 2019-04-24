package com;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.model.Role;
import com.model.User;

public class CustomUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4941188702101991922L;
	private String userName;
	private String password;
	Collection<? extends GrantedAuthority> authorities;

	public CustomUserDetails(User byUserName) {
		this.userName = byUserName.getEmpEmailID();
		this.password = byUserName.getEmpPassword();
		System.out.println(this.userName+" "+this.password);

		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		for(Role role : byUserName.getRoles()) {
			auth.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
		}
		this.authorities = auth;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
