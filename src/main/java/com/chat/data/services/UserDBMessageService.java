package com.chat.data.services;

import java.util.List;
import javax.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	
	public Page<UserDBMessage> findPublic(Pageable pageable){
		return repo.findByTarget(null, pageable);
	}

		
	// public List<UserDBMessage> findPrivate(String sender, String target){
	// 	return (List<UserDBMessage>)repo.findBySender_usernameAndTarget_username(sender, target);
	// }

	public Page<UserDBMessage> findMutual(String sender, String target, Pageable pageable){
		return repo.findMutual(sender, target, pageable);
	}

	
}
