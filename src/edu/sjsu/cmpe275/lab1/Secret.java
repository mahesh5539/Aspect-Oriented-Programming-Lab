package edu.sjsu.cmpe275.lab1;

import java.util.UUID;

public class Secret {
	public String userId;
	public String secret;
	public UUID secretId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public UUID getSecretId() {
		return secretId;
	}
	public void setSecretId(UUID secretId) {
		this.secretId = secretId;
	}
	

}
