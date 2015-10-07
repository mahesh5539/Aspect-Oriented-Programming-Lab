package edu.sjsu.cmpe275.lab1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class SecretServiceImpl implements SecretService {
	
	public static Map<String, List<Secret>> secretMap = new HashMap<String, List<Secret>>();
	public static List<Secret> myList = new ArrayList<Secret>();
	public static Map<String, List<UUID>> sharedMap = new HashMap<String, List<UUID>>();
	public static List<UUID> targetList = new ArrayList<UUID>();
	
	public UUID storeSecret(String userId, Secret secret){
		myList.add(secret);
		UUID secretId= UUID.randomUUID();
		secret.setSecretId(secretId);
		secretMap.put(userId, myList);
		return secret.getSecretId();
	}
	
	public Secret readSecret(String userId, UUID secretId){
		Secret secret = new Secret();
		for(int i=0; i<myList.size()-1;i++){
			if(myList.get(i).getSecretId() == secretId){
				secret =  myList.get(i);
			}
		}
		return secret;
	}
	
	
	public void shareSecret(String userId, UUID secretId, String targetUserId){
			targetList.add(secretId);
			sharedMap.put(targetUserId, targetList);
	}
	
	public void unshareSecret(String userId, UUID secretId, String targetUserId){
			sharedMap.remove(targetUserId);	
	}
}
