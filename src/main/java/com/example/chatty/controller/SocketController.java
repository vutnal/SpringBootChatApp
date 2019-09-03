package com.example.chatty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.example.chatty.model.Message;

@Controller
public class SocketController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/secured/room")
	//@SendToUser(broadcast =false, destinations= {"/queue/specific-user"})
	public Message sendSpecific(@Payload Message msg,  @Header("simpSessionId") String sessionId)
			throws Exception {
		Message out = new Message(msg.getFrom(), msg.getText());
		simpMessagingTemplate.convertAndSendToUser(msg.getTo(), "/queue/specific-user", out);
		return out;
	}

	@MessageMapping("/secured/chat")
	@SendToUser("/secure/history")
	public Message sendAll(@Payload Message msg, @Header("simpSessionId") String sessionId)
			throws Exception {
		Message out = new Message(msg.getFrom(), msg.getText());
		return out;
	}

}
