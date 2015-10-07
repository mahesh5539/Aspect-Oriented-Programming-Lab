package edu.sjsu.cmpe275.lab1.aspectj;




import java.util.UUID;


import org.aspectj.lang.ProceedingJoinPoint;


import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


import edu.sjsu.cmpe275.lab1.SecretServiceImpl;
import edu.sjsu.cmpe275.lab1.UnAuthorisedException;

@Aspect
public class AOPSecretServiceAspect {
	
	@Around("execution(* edu.sjsu.cmpe275.lab1.SecretServiceImpl.readSecret(..))")
	public void validateReadSecret(ProceedingJoinPoint proceedjp) throws Throwable{
		Object[] arguments = proceedjp.getArgs();
		
		if(arguments.length>0){
			String userId = (String) arguments[0];
			UUID secretId = (UUID) arguments[1];

			if(SecretServiceImpl.secretMap.containsKey(userId)){
				System.out.println(userId+" can read secret");
				proceedjp.proceed();
				System.out.println(userId+" completed the reading of secret");
			}
			
			else if(SecretServiceImpl.sharedMap.containsKey(userId)){
				System.out.println(userId+" can read the secret");
				proceedjp.proceed();
				System.out.println(userId+" completed the reading of secret");
			}
			else{
				throw new UnAuthorisedException("Secret could not be read by user "+userId);
			}
	}
		else{
			throw new Exception("Insufficient arguments");
		}
	
}
	
	@Around("execution(* edu.sjsu.cmpe275.lab1.SecretServiceImpl.shareSecret(..))")
	public void validateShareSecret(ProceedingJoinPoint proceedjp) throws Throwable{
		Object[] arguments = proceedjp.getArgs();
		
		if(arguments.length>0){
			String userId = (String) arguments[0];
			UUID secretId = (UUID) arguments[1];
			String targetId = (String) arguments[2];
			//check whether userId and targetId have same value or not
			if(userId.equals(targetId)){
				System.out.println(" Owner of the secret cannot share secret with himself");
			}
			
			else{
				if(SecretServiceImpl.secretMap.containsKey(userId)){
					for(int i=0;i<=SecretServiceImpl.myList.size()-1;i++){
						if(SecretServiceImpl.myList.get(i).secretId.equals(secretId)){
							System.out.println(userId+" can share secret with user "+ targetId);
							proceedjp.proceed();
							System.out.println(userId+" shared secret with user "+ targetId);
						}
					}
					
				}
			
				else if(SecretServiceImpl.sharedMap.containsKey(userId)){
			 
					for(int i=0; i<=SecretServiceImpl.targetList.size()-1; i++){
						if(SecretServiceImpl.targetList.get(i).equals(secretId)){
							System.out.println(userId+" can share secret with user "+ targetId);
							proceedjp.proceed();
							System.out.println(userId+" shared secret with user "+ targetId);
							break;
						}
						else{
							throw new UnAuthorisedException(userId+" cannot share secret with user"+ targetId);
						}
					}
					
					
				}
				
				else{
					throw new UnAuthorisedException(userId+" cannot share secret with user"+ targetId);
				}
			}
		}
		
		else{
			throw new Exception("Insufficient arguments");
		}
	}
	
	@Around("execution(* edu.sjsu.cmpe275.lab1.SecretServiceImpl.unshareSecret(..))")
	public void validateUnshareSecret(ProceedingJoinPoint proceedjp) throws Throwable{
		Object[] arguments = proceedjp.getArgs();
		if(arguments.length>0){
			String userId = (String) arguments[0];
			UUID secretId = (UUID) arguments[1];
			String targetId = (String) arguments[2];
			
			if(userId.equals(targetId)){
				System.out.println("Owner of the secret cannot unshare his own secret with himself");
			}
			else{
				
				if(SecretServiceImpl.secretMap.containsKey(userId)){
					for(int i=0;i<SecretServiceImpl.targetList.size()-1;i++){
						if(SecretServiceImpl.targetList.contains(secretId)){
							
							System.out.println(userId+" can unshare secret with user"+ targetId);
							proceedjp.proceed();
							System.out.println(userId+" unshared secret with user"+ targetId);
							break;
						}
					}
					
				}
				else if(SecretServiceImpl.sharedMap.containsKey(userId)){
					for(int i=0; i<SecretServiceImpl.targetList.size()-1; i++){
						if(SecretServiceImpl.targetList.get(i) == secretId){
							System.out.println(userId+" cannot unshare secret shared with "+targetId);
						}
					}
				}
				else{
					throw new UnAuthorisedException(userId+" is unathorised userId to unshare the secret with "+targetId);
				}
			}
			
		}
		else{
			throw new Exception("Insufficient arguments");
		}
	}

}
