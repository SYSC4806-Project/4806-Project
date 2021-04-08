package com.sysc4806project.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.stereotype.Component;


@Aspect
@Component
public class Performance {

    Logger log = (Logger) LoggerFactory.getLogger(Performance.class);

    @Around("@annotation(com.sysc4806project.aop.GetExecutionTime)")
    public Object getExecutionTime(ProceedingJoinPoint p) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object obj = p.proceed();
        long endTime = System.currentTimeMillis();

        log.info("Method: " + p.getSignature() + "Execution Time: " + (endTime - startTime));
        return obj;

    }
}

