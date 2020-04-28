package com.chat.data.repos;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.chat.data.models.UserDBMessage;
import org.springframework.data.jpa.repository.Query;

import com.chat.data.models.User;
	

@Repository
public interface UserDBMessageRepo extends CrudRepository<UserDBMessage, Long> {

	public List<UserDBMessage> findByTarget(User target);
	public List<UserDBMessage> findBySender_usernameAndTarget_username(String sender, String target);
	
	@Query("SELECT x FROM UserDBMessage x WHERE (x.sender.username = ?1 AND x.target.username = ?2) OR (x.sender.username = ?2 AND x.target.username = ?1)")
	public List<UserDBMessage> findMutual(String sender, String target);
}
