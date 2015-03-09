package com.ht.b2attr.b2attr_service.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspect {
	@Pointcut("execution(* com.ht.b2attr.b2attr_service.SocketClient.receive(String)) && args(msg)")
	public void receive(String msg) {
	}

	@Before("receive(msg)")
	public void before(String msg) {
		System.out.println("before method." + msg);
	}

	@After("receive(msg)")
	public void after(String msg) {
		System.out.println("after method.");
	}

	@Around("receive(msg)")
	public void monitorReceive(ProceedingJoinPoint joinpoint,String msg) {
		System.out.println("monitor with aop around start...");
		System.out.println("parameters:" + joinpoint.getArgs()[0].toString());
		try {
			joinpoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("monitor with aop around start...");
	}
}
