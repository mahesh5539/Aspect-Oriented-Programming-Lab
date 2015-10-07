package edu.sjsu.cmpe275.lab1;

import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SecretMain {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("Spring.xml");
		SecretService secretService =  (SecretService) ctx.getBean("secretServiceImpl");
		
		
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		secretService.shareSecret("Alice", aliceSecret, "Bob");
    	secretService.shareSecret("Bob", aliceSecret, "Carl");
    	secretService.unshareSecret("Alice", aliceSecret, "Bob");
    	secretService.shareSecret("Bob", aliceSecret, "Carl");
		
		UUID aliceSecret1 = secretService.storeSecret("Alice", new Secret());
    	UUID aliceSecret2 = secretService.storeSecret("Alice", new Secret());
    	
    	if(aliceSecret1!=aliceSecret2)
    		System.out.println("test pass");
    	else
    		System.out.println("test fail");
		
		
		
	}

}
