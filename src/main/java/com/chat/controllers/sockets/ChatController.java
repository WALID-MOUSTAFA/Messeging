package com.chat.controllers.sockets;

import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ChatController {

	private Logger logger= LoggerFactory.getLogger(ChatController.class);
	
	@MessageMapping("/chat/message")
	@SendTo("/topic/chat")
	public Message index(Message message){
		logger.info(message.getBody());
		return message;
	}
}

class Message {
	private String body;

	public void setBody(String body){
		this.body= body;
	}

	public String getBody(){
		return this.body;
	}
}
