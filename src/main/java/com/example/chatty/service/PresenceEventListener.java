package com.example.chatty.service;

import com.example.chatty.util.ChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.example.chatty.model.Presence;

@Configuration
public class PresenceEventListener {
	
	PresenceManager presenceManager;
	
	@Autowired
	public PresenceEventListener(PresenceManager presenceManager) {
		this.presenceManager = presenceManager;
	}
	
	@EventListener
	private void handleSessionConnected(SessionConnectEvent event) {
		System.out.println("Connected....");
		Presence presence = new Presence();
		presence.setNodeId(ChatUtil.getUniqueNodeId());
		presence.setTime(System.currentTimeMillis());
		presence.setUserId(event.getUser().getName());
		System.out.println(presence.toString());
		presenceManager.updateHeartbeat(presence);
	}
	
	@EventListener
	private void handleSessionDisconnect(SessionDisconnectEvent event) {
		System.out.println("Disconnected....");
	}

}
