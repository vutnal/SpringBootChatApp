package com.example.chatty.service;

import com.example.chatty.model.Presence;

public interface PresenceManager {
	
	void updateHeartbeat(Presence presence);
	public Presence getHeartbeat(String userName);
	public Presence getPresenceInfo(String userName);

}
