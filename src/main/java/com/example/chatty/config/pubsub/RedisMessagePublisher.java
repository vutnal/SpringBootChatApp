package com.example.chatty.config.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import com.example.chatty.model.Message;

@Service
public class RedisMessagePublisher implements MessagePublisher {
	 
    @Autowired
    private RedisTemplate<String, Message> redisTemplate;
    
    @Autowired
    private ChannelTopic topic;
 
    public RedisMessagePublisher() {
    }
    
 
    public void publish(Message message) {
    	redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
