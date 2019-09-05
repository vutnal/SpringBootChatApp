package com.example.chatty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.example.chatty.model.Message;
import com.example.chatty.service.MessageManager;

@Controller
public class SocketController {

	@Autowired
	MessageManager messageManager;

	@MessageMapping("/secured/room")
	public void sendSpecific(@Payload Message msg,  @Header("simpSessionId") String sessionId)
			throws Exception {
		
		messageManager.sendToUser(msg);
	}

	@MessageMapping("/secured/chat")
	@SendToUser("/secure/history")
	public Message sendAll(@Payload Message msg, @Header("simpSessionId") String sessionId)
			throws Exception {
		Message out = new Message(msg.getFrom(), msg.getText());
		return out;
	}

}
