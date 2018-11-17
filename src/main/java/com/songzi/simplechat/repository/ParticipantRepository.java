package com.songzi.simplechat.repository;

import com.songzi.simplechat.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author Cliff
 */
@Service("participantRepository")
public class ParticipantRepository {

	private Map<String, UserEntity> activeSessions = new ConcurrentHashMap<>();

	public void add(String sessionId, UserEntity event) {
		activeSessions.put(sessionId, event);
	}

	public UserEntity getParticipant(String sessionId) {
		return activeSessions.get(sessionId);
	}

	public void removeParticipant(String sessionId) {
		activeSessions.remove(sessionId);
	}

	public Map<String, UserEntity> getActiveSessions() {
		return activeSessions;
	}

	public void setActiveSessions(Map<String, UserEntity> activeSessions) {
		this.activeSessions = activeSessions;
	}
}
