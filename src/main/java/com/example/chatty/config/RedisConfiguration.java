package com.example.chatty.config;

import com.example.chatty.util.ChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.chatty.config.pubsub.RedisMessageSubscriber;
import com.example.chatty.model.Message;

@Configuration
public class RedisConfiguration {

	@Value("${spring.redis.host}")
	private String REDIS_HOSTNAME;

	@Value("${spring.redis.port}")
	private int REDIS_PORT;

	@Bean
	protected JedisConnectionFactory jedisConnectionFactory() {

		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(REDIS_HOSTNAME, REDIS_PORT);

		JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build();

		JedisConnectionFactory factory = new JedisConnectionFactory(configuration, jedisClientConfiguration);

		factory.afterPropertiesSet();

		return factory;

	}

	@Bean
	public <T> RedisTemplate<String, T> redisTemplate() {

		final RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();

		redisTemplate.setKeySerializer(new StringRedisSerializer());

		redisTemplate.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));

		redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

		redisTemplate.setConnectionFactory(jedisConnectionFactory());

		return redisTemplate;

	}
	
	
//	@Bean
//	public RedisTemplate<String, Message> redisMessageTemplate() {
//
//		final RedisTemplate<String, Message> redisTemplate = new RedisTemplate<String, Message>();
//
//		redisTemplate.setKeySerializer(new StringRedisSerializer());
//
//		redisTemplate.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
//
//		redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Message>(Message.class));
//
//		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Message>(Message.class));
//
//		redisTemplate.setConnectionFactory(jedisConnectionFactory());
//
//		return redisTemplate;
//
//	}
	
	@Bean
	ChannelTopic topic() {
	    String nodeId = ChatUtil.getUniqueNodeId();
		return new ChannelTopic(nodeId+"-topic");
	}
	
	@Autowired
    RedisMessageSubscriber redisMessageSubscriber;

    @Bean
    MessageListenerAdapter messageListener( ) {
        return new MessageListenerAdapter(redisMessageSubscriber);
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container
                = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListener(), topic());
        return container;
    }
}
