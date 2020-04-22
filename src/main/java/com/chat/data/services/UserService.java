package com.chat.data.services;

import java.util.List;
import javax.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.chat.data.repos.UserRepo;
import com.chat.data.models.User;

@Service
@Transactional
public class UserService{
	

	
	@Autowired
	UserRepo repo;

	public void save(User user) {
		repo.save(user);
	}


	public List<User> findAll(){
		return repo.findAll();
	}

	public List<User> findByUsername(String username){
		List l= repo.findByUsername(username);

		if(l.isEmpty()){
			throw new NotFoundException();
		}

		return l;
	}
	
}
