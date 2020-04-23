package com.chat.controllers.sockets;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chat.data.models.User;

@Controller
public class ChatController {

	private Logger logger= LoggerFactory.getLogger(ChatController.class);
	
	@MessageMapping("/chat/message")
	@SendTo("/topic/chat")
	public Message index(Message message, Authentication authentication){
		
	        message.setUsername((String) authentication.getPrincipal());
		return message;
	}
}

class Message {

	private String body;
	private String username;

	
	public void setBody(String body){
		this.body= body;
	}

	
	public String getBody(){
		return this.body;
	}


	
	public void setUsername(String username){
		this.username= username;
	}
	
	
	public String getUsername(){
		return this.username;
	}


	
	
}
