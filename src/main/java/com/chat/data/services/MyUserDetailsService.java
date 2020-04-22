package com.chat.data.services;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import com.chat.data.repos.UserRepo;
import com.chat.data.models.User;
import com.chat.data.models.UserPermission;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username){
		User user= userRepo.findByUsername(username).get(0);

		if(user == null){
			throw new UsernameNotFoundException(username);
		}

		return new MyUserPrincple(user);
	}
	
	
}


class MyUserPrincple implements UserDetails {
	private User user;


	public MyUserPrincple(User user){
		this.user= user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		
		Collection<UserPermission> perms= this.user.getUserPermissions();
		List<UserPermission> l= (List<UserPermission>) perms;
		List<GrantedAuthority> grantedAuthority = new ArrayList<GrantedAuthority>();
		
		l.forEach(e -> {
				grantedAuthority.add(new SimpleGrantedAuthority(e.getPermission().getName()));
			});

		return (Collection<GrantedAuthority>) grantedAuthority;
	}


	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
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
