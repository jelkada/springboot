package com.jelkada.aopdemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class LogAspect {
	
	@Pointcut("execution(void addUser(..))")
	private void addUserPointcut() {}

	@Before("addUserPointcut()")
	public void beforeLogUser() {
		System.out.println("\n >> Executing beforeLogUser advice");
	}
	
	@Around("execution(* slowTraffic(..))")
	public Object aroundService(ProceedingJoinPoint proceedJP) throws Throwable {
		
		MethodSignature method = (MethodSignature)proceedJP.getSignature();
		System.out.println("\n >> @Around methodSig: " + method);

		long beginTime = System.currentTimeMillis();
		Object result = proceedJP.proceed();
		long endTime = System.currentTimeMillis();
		long duration = endTime - beginTime;
		System.out.println(" >> @Around duration: " + duration / 1000 + " seconds");
		
		return result;
	}
}
