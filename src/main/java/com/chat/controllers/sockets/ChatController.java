package com.chat.controllers.sockets;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.chat.data.models.User;
import com.chat.pojo.Message;

@Controller
public class ChatController {

	private Logger logger= LoggerFactory.getLogger(ChatController.class);
	private static List<String> onlineUsers; 

	@Autowired
	private SimpUserRegistry simpUserRegistry;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	

	@MessageMapping("/chat/message")
	@SendTo("/topic/chat")
	public Message index(Message message, Authentication authentication){

	        message.setUsername((String) authentication.getPrincipal());
		return message;
	}

	
}

