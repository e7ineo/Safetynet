package com.e7i.safetynetapi.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopControlJson {
	
//	@Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping)")
//	public void pointcutAround() {}
//	
//	@Around("pointcutAround() && args(@RequestBody body)")
//	public Object controlJson(ProceedingJoinPoint pjp, Object body) throws Throwable {
//		System.out.println("ImHere");
//		var result = pjp.proceed();
//		return result;
//		
//	}
}
