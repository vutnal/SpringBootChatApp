package com.example.chatty.config.pubsub;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RedisMessageSubscriber implements MessageListener {

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	RedisMessageSubscriber(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	}

	private SimpMessagingTemplate simpMessagingTemplate;

	public void onMessage(Message message, byte[] pattern) {
		//How to listen only for specific messages?
		try {
			com.example.chatty.model.Message chatMessage = objectMapper.readValue(message.toString(),
					com.example.chatty.model.Message.class);

			System.out.println("Message received: " + message.toString());
			simpMessagingTemplate.convertAndSendToUser(chatMessage.getTo(), "/queue/specific-user", chatMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
