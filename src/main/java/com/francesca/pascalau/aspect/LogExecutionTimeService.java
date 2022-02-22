package com.francesca.pascalau.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogExecutionTimeService {

    /**
     * This pointcut defines that it should be executed for all methods from within
     * the classes annotated with the @Service annotation.
     */
    @Pointcut("@within(org.springframework.stereotype.Service)")
    private void isServiceMethod() {
    }

    /**
     * This pointcut defines that it should be executed for all methods from within the service package.
     */
    @Pointcut("within(com.francesca.pascalau.domain.service.*)\n")
    private void isServiceMethod2() {
    }

    @Around("@annotation(com.francesca.pascalau.aspect.annotation.LogExecutionTime) && isServiceMethod()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
