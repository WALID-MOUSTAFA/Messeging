package com.chat.data.repos;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chat.data.models.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	public List<User> findByUsername(String username);
}
