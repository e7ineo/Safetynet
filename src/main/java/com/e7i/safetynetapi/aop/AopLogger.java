package com.e7i.safetynetapi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopLogger {
	
	private static Logger logger = LoggerFactory.getLogger(AopLogger.class);
	
	@AfterReturning(pointcut = ("within(com.e7i.safetynetapi.controller..*)"), returning = "entity")
	private void getMapLog(JoinPoint jp, ResponseEntity<?> entity) {
		getLog(jp, entity);	
	}
	
	private void getLog(JoinPoint jp, ResponseEntity<?> entity) {
		if(entity.getStatusCode().is2xxSuccessful()) {
			logger.info(jp.getSignature().getName() + " OK");
		} else if(entity.getStatusCode().is4xxClientError()) {
			logger.info(jp.getSignature().getName() + " NOK");
		}
	}
}
//	************** Old way with a lot of repetition *****************
//	
//	@Pointcut("within(com.e7i.safetynetapi.controller..*) && @annotation(org.springframework.web.bind.annotation.GetMapping)")
//	private void pointcutGet() {}
//	
//	@Pointcut("within(com.e7i.safetynetapi.controller..*) && @annotation(org.springframework.web.bind.annotation.PostMapping)")
//	private void pointcutPost() {}
//	
//	@Pointcut("within(com.e7i.safetynetapi.controller..*) && @annotation(org.springframework.web.bind.annotation.PutMapping)")
//	private void pointcutPut() {}
//	
//	@Pointcut("within(com.e7i.safetynetapi.controller..*) && @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
//	private void pointcutDelete() {}
//	
//	@AfterReturning(pointcut = "pointcutGet()", returning = "entity")
//	private void getMapLog(JoinPoint jp, ResponseEntity<?> entity) {
//		getLog(jp, entity);	
//	}
//	
//	@AfterReturning(pointcut = "pointcutPost()", returning = "entity")
//	private void postMapLog(JoinPoint jp, ResponseEntity<?> entity) {   
//		getLog(jp, entity);	
//	}
//	
//	@AfterReturning(pointcut = "pointcutPut()", returning = "entity")
//	private void putMapLog(JoinPoint jp, ResponseEntity<?> entity) {   
//		getLog(jp, entity);
//	}
//	
//	@AfterReturning(pointcut = "pointcutDelete()", returning = "entity")
//	private void deleteMapLog(JoinPoint jp, ResponseEntity<?> entity) {   
//		getLog(jp, entity);
//	}
