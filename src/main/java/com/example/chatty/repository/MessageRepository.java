//package com.example.chatty.repository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.cassandra.core.CassandraTemplate;
//import org.springframework.stereotype.Repository;
//
//import com.example.chatty.entity.MessageEntity;
//
////@Repository
//public class MessageRepository {
//
//	//@Autowired
//	CassandraTemplate cassandraTemplate;
//
//	public MessageEntity insertMessage(MessageEntity message) {
//		return cassandraTemplate.insert(message);
//
//	}
//
//	public MessageEntity updateMessage(MessageEntity message) {
//		return cassandraTemplate.update(message);
//
//	}
//
//	public MessageEntity getMessage(String fromUser) {
//		return null;
//	}
//
//	public MessageEntity deleteMessage(String fromUser) {
//		return null;
//
//	}
//
//}
