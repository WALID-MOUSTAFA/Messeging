package com.chat.data.services;

import java.util.List;
import javax.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;

import com.chat.data.repos.UserDBMessageRepo;
import com.chat.data.models.UserDBMessage;

@Service
@Transactional
public class UserDBMessageService{
	
	@Autowired
	UserDBMessageRepo repo;

	
	public void save(UserDBMessage messages) {
		repo.save(messages);
	}
	

	public List<UserDBMessage> findAll(){
		return (List<UserDBMessage>)repo.findAll();
	}
	
	public List<UserDBMessage> findPublic(){
		return (List<UserDBMessage>)repo.findByTarget(null);
	}

		
	public List<UserDBMessage> findPrivate(String sender, String target){
		return (List<UserDBMessage>)repo.findBySender_usernameAndTarget_username(sender, target);
	}

	public List<UserDBMessage> findMutual(String sender, String target){
		return (List<UserDBMessage>)repo.findMutual(sender, target);
	}

	
}
