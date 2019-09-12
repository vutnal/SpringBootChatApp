package com.example.chatty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ChattyApplication{

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
//		return  application.sources(ChattyApplication.class);
//	}
	public static void main(String[] args) {
		SpringApplication.run(ChattyApplication.class, args);
	}

}
