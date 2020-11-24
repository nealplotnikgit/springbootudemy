package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
@Configuration
@Aspect
@Slf4j
public class AspectConfig {
	private static int count = 0;
//@Before(value = "execution(* com.example.demo.controller.*.*(..))")//all methods with any # of parameters inside every class of the package
@Before(value = "execution(* com.example.demo.controller.*.*(..)) and args(object)")//only methods with 1 param
	public void beforeAdvice(JoinPoint j,Object object) {//object must match args
		log.info(++count + " before advice" + object.toString() );
	}

@After(value = "execution(* com.example.demo.controller.*.*(..)) and args(object)")//only methods with 1 param
public void afterMethodAdvice(JoinPoint j,Object object) {//object must match args
	log.info(++count + " after advice" + object );
}

@AfterReturning(value = "execution(* com.example.demo.controller.*.*(..)) and args(object)", returning = "returnObj")//only methods with 1 param
public void afterReturningAdvice(JoinPoint j,Object object,Object returnObj) {//object must match args
	log.info(++count + " after advice returning = " + returnObj );
}

@Around(value = "execution(* com.example.demo.controller.*.*(..)) and args(object)")//only methods with 1 param
public void aroundAdvice(ProceedingJoinPoint j,Object object) {//object must match args
	

	log.info(++count + " around A" );
	Object returnObject = null;
	try {
		returnObject = j.proceed();
	} catch (Throwable e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	log.info(++count + " around B" + returnObject );
	
}

}
