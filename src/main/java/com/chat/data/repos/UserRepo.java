package com.chat.data.repos;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chat.data.models.User;


@Repository
public interface UserRepo extends CrudRepository<User, Long>{

	public List<User> findByUsername(String username);


}
