package com.sysc4806project.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAdvice {

    Logger log = (Logger) LoggerFactory.getLogger(LoggingAdvice.class);


    @Around("@annotation(com.sysc4806project.aop.GetLogInfo)")
    public void getLogInfo(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName =pjp.getSignature().getName();
        String className= pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();
        log.info("method invoked " + className + " : " + methodName + "()" + "arguments : " + mapper.writeValueAsString(array));
    }

}

