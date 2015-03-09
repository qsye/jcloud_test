package com.ht.b2attr.b2attr_service.aspects;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspect {

	public void before()
	{
		System.out.println("before method.");		
	}
	
	public void after()
	{
		System.out.println("after method.");		
	}
	
	public void monitorReceive(ProceedingJoinPoint joinpoint)
	{
		System.out.println("monitor with aop around start..." );
		System.out.println("parameters:" + joinpoint.getArgs()[0].toString() );
		try {
			joinpoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("monitor with aop around start...");
	}
}
