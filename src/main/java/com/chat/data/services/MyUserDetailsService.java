package com.chat.data.services;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
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

		MyUserPrincple myUserPrincple = new MyUserPrincple(user);
		
		return myUserPrincple;
	}
	
	
}


