package com.example.chatty.config.pubsub;

import com.example.chatty.model.Presence;
import com.example.chatty.service.PresenceManager;
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

    @Autowired
    private PresenceManager presenceManager;
 
    public RedisMessagePublisher() {
    }
    
 
    public void publish(Message message)
    {
        String toUser = message.getTo();
        Presence presence =presenceManager.getPresenceInfo(toUser);
        //presence.getNodeId();
    	redisTemplate.convertAndSend(presence.getNodeId()+"-topic", message);
    }
}
