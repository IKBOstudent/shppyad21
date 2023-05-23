package com.example.shppyad21;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ServiceExecutionTimeAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceExecutionTimeAspect.class);

    @Around("allServiceMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            LOGGER.error("Exception occurred during method execution");
            result = null;
        } finally {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            String methodName = joinPoint.getSignature().getName();
            String className = joinPoint.getTarget().getClass().getSimpleName();
            LOGGER.info("Execution of {}.{} took {} ms", className, methodName, duration);
        }
        return result;
    }

    @Pointcut("execution(* com.example.shppyad21.Departure.DepartureService.*(..)) || " +
              "execution(* com.example.shppyad21.PostOffice.PostOfficeService.*(..))")
    public void allServiceMethods() {}
}
