package com.example.chatty.config.pubsub;

import com.example.chatty.model.Message;

public interface MessagePublisher {
    void publish(Message message);
}
