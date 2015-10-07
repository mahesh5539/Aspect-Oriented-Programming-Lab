package edu.sjsu.cmpe275.lab1.jUnit;

import java.util.UUID;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import edu.sjsu.cmpe275.lab1.Secret;
import edu.sjsu.cmpe275.lab1.SecretService;
import edu.sjsu.cmpe275.lab1.SecretServiceImpl;


@ContextConfiguration(locations = { "/Spring.xml" })

public class SecretServiceTest {
	ApplicationContext context;
	SecretService secretService;

    @Before
    public void setUp() throws Exception {
    	context = new FileSystemXmlApplicationContext("src/Spring.xml");
    	secretService=  (SecretService)context.getBean("secretServiceImpl");
    }
      
    
    
   @Test()
    public void testA() {
    	try{
    		
   	 System.out.println("testA");
   	UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
   	 secretService.readSecret("Bob", aliceSecret);
   	 assert true;
    	}catch(Exception e){
    		
    		//e.printStackTrace();
    		assert false;
    		System.out.println("Read Operation failed: Exception ");
    	//	e.printStackTrace();
    	}
    }

    @Test
    public void testB(){
    	try{
   	 System.out.println("testB");
   	 UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
   	 secretService.shareSecret("Alice", aliceSecret, "Bob");
   	 secretService.readSecret("Bob",aliceSecret);
   	 assert true;
    	}catch(Exception e){
    		
    		//e.printStackTrace();
    		assert false;
    		System.out.println("Read Operation failed: Exception ");
    	//	e.printStackTrace();
    	}
    }
    
    @Test
    public void testC(){
    	try{
    	System.out.println("testC");
    	UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
    	secretService.shareSecret("Alice", aliceSecret, "Bob");
    	secretService.shareSecret("Bob", aliceSecret, "Carl");
    	secretService.readSecret("Carl",aliceSecret);
    	assert true;
    	}catch(Exception e){
    		
    		//e.printStackTrace();
    		assert false;
    		System.out.println("Read Operation failed: Exception ");
    	//	e.printStackTrace();
    	}
    }
    
    @Test
    public void testD(){
    	try{
    	System.out.println("testD");
    	UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
    	UUID carlSecret = secretService.storeSecret("Carl", new Secret());//create new UUID so that both uuId will be different
    	secretService.shareSecret("Alice", aliceSecret, "Bob");
    	secretService.shareSecret("Bob", carlSecret, "Alice");
    	assert true;
    	}catch(Exception e){
    		
    		//e.printStackTrace();
    		assert false;
    		System.out.println("Read Operation failed: Exception ");
    	//	e.printStackTrace();
    	}
    	
    }
    
    @Test
    public void testE(){
    	try{
    	System.out.println("testE");
    	UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
    	secretService.shareSecret("Alice", aliceSecret, "Bob");
    	secretService.shareSecret("Bob", aliceSecret, "Carl");
    	secretService.unshareSecret("Alice", aliceSecret, "Carl" );
    	secretService.readSecret("Carl",aliceSecret);
    	assert true;
    	}catch(Exception e){
    		
    		//e.printStackTrace();
    		assert false;
    		System.out.println("Read Operation failed: Exception ");
    	//	e.printStackTrace();
    	}
    }
    
    @Test
    public void testF(){
    	try{
    	System.out.println("testF");
    	UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
    	secretService.shareSecret("Alice", aliceSecret, "Bob");
    	secretService.shareSecret("Alice", aliceSecret, "Carl");
    	secretService.shareSecret("Carl", aliceSecret, "Bob");
    	secretService.unshareSecret("Alice", aliceSecret, "Bob");
    	secretService.readSecret("Bob",aliceSecret); //BOb should be entered once check for it contains then donot allow to share
    	assert true;
    	}catch(Exception e){
    		
    		//e.printStackTrace();
    		assert false;
    		System.out.println("Read Operation failed: Exception ");
    	//	e.printStackTrace();
    	}
    	
    }
    
    @Test
    public void testG(){
    	try{
    	System.out.println("testG");
    	UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
    	secretService.shareSecret("Alice", aliceSecret, "Bob");
    	secretService.shareSecret("Bob", aliceSecret, "Carl");
    	secretService.unshareSecret("Bob", aliceSecret, "Carl");
    	secretService.readSecret("Carl",aliceSecret);
    	assert true;
    	}catch(Exception e){
    		
    		//e.printStackTrace();
    		assert false;
    		System.out.println("Read Operation failed: Exception ");
    	//	e.printStackTrace();
    	}
    }
    
    @Test
    public void testH(){
    	try{
    	System.out.println("testH");
    	UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
    	secretService.shareSecret("Alice", aliceSecret, "Bob");
    	secretService.unshareSecret("Carl", aliceSecret, "Bob");
    	assert true;
    	}catch(Exception e){
    		
    		//e.printStackTrace();
    		assert false;
    		System.out.println("Read Operation failed: Exception ");
    	//	e.printStackTrace();
    	}
    }
    
    @Test
    public void testI(){
    	try{
    	System.out.println("testI");
    	UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
    	secretService.shareSecret("Alice", aliceSecret, "Bob");
    	secretService.shareSecret("Bob", aliceSecret, "Carl");
    	secretService.unshareSecret("Alice", aliceSecret, "Bob");
    	secretService.shareSecret("Bob", aliceSecret, "Carl");
    	assert true;
    	}catch(Exception e){
    		
    		//e.printStackTrace();
    		assert false;
    		System.out.println("Read Operation failed: Exception ");
    	//	e.printStackTrace();
    	}
    }
    
    @Test
    public void testJ(){
    	
    	UUID aliceSecret1 = secretService.storeSecret("Alice", new Secret());
    	UUID aliceSecret2 = secretService.storeSecret("Alice", new Secret());
    	
    	if(aliceSecret1 !=aliceSecret2){
    		assert true;
    	}
    	else{
    		assert false;
    	}
    }
}
