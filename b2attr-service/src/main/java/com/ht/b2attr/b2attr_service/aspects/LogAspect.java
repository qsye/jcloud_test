package com.ht.b2attr.b2attr_service.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspect {
	@Pointcut("execution(* com.ht.b2attr.b2attr_service.SocketClient.receive(String)) && args(msg) ")
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
	public void monitorReceive(ProceedingJoinPoint joinpoint, String msg) {
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

	@Pointcut("execution(int com.ht.b2attr.b2attr_service.DAO.*.*(..))")
	public void anyMethod() {

	}

	@Around(value = "anyMethod()")
	public int beforeAnyMethod(ProceedingJoinPoint joinpoint) throws Throwable {
		System.out.println("Method name:" + joinpoint.getSignature().getName());
		long start = System.currentTimeMillis();

		Object obj=joinpoint.proceed();
		long end = System.currentTimeMillis();
		System.out.println("cost " + (end - start) + " ms");
		return (Integer)obj;
	}

	@AfterReturning(value = "execution(* com.ht.b2attr.b2attr_service.DAO.*.*(..))", argNames = "retVal", returning = "retVal")
	public int afterDAO(JoinPoint joinPoint, int retVal) {
		System.out.println("(log in afterReturning)return value:" + retVal);
		return retVal;
	}
}
