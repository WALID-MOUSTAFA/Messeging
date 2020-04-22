package com.chat.pogo;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class LoginPOGO {

	@NotNull(message= "username is required")
	@Length(min= 4)
	private String username;
	

	@NotNull(message= "username is required")
	@Length(min= 4)
	private String password;


	public void setUsername(String username){
		this.username= username;
	}

	public void setPassword(String password){
		this.password= password;
	}


	public String getUsername(){
		return this.username;
	}
	

	
	public String getPassword(){
		return this.password;
	}

	
}
