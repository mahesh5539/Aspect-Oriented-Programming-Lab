package edu.sjsu.cmpe275.lab1;

import java.util.UUID;

public interface SecretService {
	UUID storeSecret(String userId, Secret secret);
	
	Secret readSecret(String userId, UUID secretId);
	
	void shareSecret(String userId, UUID secretId, String targetUserId);
	
	void unshareSecret(String userId, UUID secretId, String targetUserId);

}
