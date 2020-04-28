package com.chat.controllers.sockets;

import java.util.Date;
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
import org.springframework.messaging.handler.annotation.DestinationVariable;

import com.chat.data.models.DBMessage;
import com.chat.data.models.User;
import com.chat.pojo.Message;
import com.chat.data.services.UserService;
import com.chat.data.services.UserDBMessageService;
import com.chat.data.models.UserDBMessage;

@Controller
public class ChatController {

	private Logger logger= LoggerFactory.getLogger(ChatController.class);
	private static List<String> onlineUsers; 

	@Autowired
	private SimpUserRegistry simpUserRegistry;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
	private UserDBMessageService userDBMessageService;

	@Autowired
	private UserService userService;
	
	@MessageMapping("/chat/message")
	@SendTo("/topic/chat")
	public Message publicMessage(Message message, Authentication authentication){

		String username = (String) authentication.getPrincipal();

		message.setUsername(username);		

		User user= userService.findByUsername(username).get(0);
		
		DBMessage dbMessage= new DBMessage();
		dbMessage.setBody(message.getBody());
		dbMessage.setCreatedAt(new Date());
		
		UserDBMessage udbmsg= new UserDBMessage();
		udbmsg.setMessage(dbMessage);
		udbmsg.setSender(user);
		udbmsg.setTarget(null);
		userDBMessageService.save(udbmsg);
		
		
		return message;
	}
	
	
	@MessageMapping("/chat/message.private/{targetUsername}")
	public void privateMessage(Message message,
				   @DestinationVariable("targetUsername") String targetUsername,
				   Authentication authentication)
	{
		
		String username= authentication.getName();
		message.setUsername(username);
		
		User sender= userService.findByUsername(username).get(0);
		User target= userService.findByUsername(targetUsername).get(0);
		
		DBMessage dbMessage= new DBMessage();
		dbMessage.setBody(message.getBody());
		dbMessage.setCreatedAt(new Date());
		
		UserDBMessage udbmsg= new UserDBMessage();
		udbmsg.setMessage(dbMessage);
		udbmsg.setSender(sender);
		udbmsg.setTarget(target);
		userDBMessageService.save(udbmsg);

		this.simpMessagingTemplate.convertAndSendToUser(targetUsername, "/topic/chat.private", message);
		this.simpMessagingTemplate.convertAndSendToUser(authentication.getName(), "/topic/chat.private", message);

	}
	
}

