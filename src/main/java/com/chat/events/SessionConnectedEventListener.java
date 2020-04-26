package com.chat.events;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.chat.pojo.OnlineUsers;


@Component
public class SessionConnectedEventListener implements ApplicationListener<SessionConnectedEvent> {
	
	
	@Autowired private ApplicationEventPublisher applicationEventPublisher;
	@Autowired private SimpUserRegistry simpUserRegistry;
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;


	@EventListener
	@Override
	public void onApplicationEvent(SessionConnectedEvent  applicationEvent) {
		
		Set<SimpUser> usersSet = simpUserRegistry.getUsers();
	        OnlineUsers onlineUsers = new OnlineUsers();
		
		List<String> l = new ArrayList<String>();
		for(SimpUser u : usersSet){
			l.add(u.getName());
		}
		onlineUsers.setUsers(l);
		this.simpMessagingTemplate.convertAndSend("/topic/chat.online-users", onlineUsers);

	}
}

