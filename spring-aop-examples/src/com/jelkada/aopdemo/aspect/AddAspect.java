package com.jelkada.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jelkada.aopdemo.User;

@Aspect
@Component
@Order(2)
public class AddAspect {

	@Pointcut("execution(* addUser(..))")
	private void addUserPointcut() {}

	@Pointcut("execution(* updateUser(..))")
	private void updateUserPointcut() {}

	@Before("addUserPointcut() || updateUserPointcut()")
	public void beforeAddUser(JoinPoint theJoinPoint) {
		
		System.out.println("\n >> Executing beforeAddUser advice");
		
		MethodSignature methodSig = (MethodSignature)theJoinPoint.getSignature();
		System.out.println(" >> methodSig: " + methodSig);
		
		Object[] args = theJoinPoint.getArgs();
		for (Object arg: args) {
			System.out.println(" >> arg: " + arg);
			if (arg instanceof User) {
				System.out.println("firstName: " + ((User) arg).getFirstName());
				System.out.println("lastName: " + ((User) arg).getLastName());
			}
		}	
	}
}
