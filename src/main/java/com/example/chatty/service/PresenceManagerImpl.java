package com.example.chatty.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.example.chatty.config.RedisUtil;
import com.example.chatty.model.Presence;

@Configuration
public class PresenceManagerImpl implements PresenceManager {

	public static final String TABLE_PRESENCE = "TABLE_PRESENCE";

	public static final String PRESENCE = "PRESENCE_";

	private RedisUtil<Presence> presenceRedisUtil;

	@Autowired
	public PresenceManagerImpl(RedisUtil<Presence> presenceRedisUtil) {
		this.presenceRedisUtil = presenceRedisUtil;
	}

	@Override
	public void updateHeartbeat(Presence presence) {
		this.presenceRedisUtil.putMap(TABLE_PRESENCE, PRESENCE + presence.getUserId(), presence);
		this.presenceRedisUtil.setExpire(TABLE_PRESENCE, 1, TimeUnit.DAYS);
	}

}
