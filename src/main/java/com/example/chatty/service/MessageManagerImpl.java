package com.example.chatty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.chatty.config.pubsub.RedisMessagePublisher;
import com.example.chatty.model.Message;

@Service
public class MessageManagerImpl implements MessageManager {

	@Autowired
	private RedisMessagePublisher publisher;

	public MessageManagerImpl() {
	}

	@Override
	public void sendToUser(Message msg) {
		publisher.publish(msg);

	}

}
