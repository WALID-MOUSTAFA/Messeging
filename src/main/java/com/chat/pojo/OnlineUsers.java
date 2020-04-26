package com.chat.pojo;

import java.util.List;
	
public class OnlineUsers {

       	private String body;
	
	private List<String> users;

	public void setUsers (List<String> users)
	{
		this.users = users;
	}
	
	public List<String> getUsers ()
	{
		return this.users;
	}

	public void setBody(String body){
		this.body= body;
	}

	
	public String getBody(){
		return this.body;
	}

	
}
