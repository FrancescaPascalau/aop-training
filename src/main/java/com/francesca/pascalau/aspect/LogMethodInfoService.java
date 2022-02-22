package com.francesca.pascalau.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogMethodInfoService {

    @Around("@annotation(com.francesca.pascalau.aspect.annotation.LogMethodInfo)")
    public Object logMethodInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        var methodName = joinPoint.getSignature().getName();
        var className = joinPoint.getSignature().getDeclaringTypeName();

        System.out.println("\nExecuting Method: " + methodName + "() from Class: " + className + " with Args: ");

        printArgs(joinPoint);
        printReturnValue(joinPoint);

        return joinPoint.proceed();
    }

    private void printArgs(ProceedingJoinPoint joinPoint) {
        Object[] signatureArgs = joinPoint.getArgs();
        for (int i = 0; i < signatureArgs.length; i++) {
            Object signatureArg = signatureArgs[i];
            System.out.println(++i + ". " + signatureArg);
        }
    }

    private void printReturnValue(ProceedingJoinPoint joinPoint) throws Throwable {
        var returnValue = joinPoint.proceed();

        if (returnValue != null)
            System.out.println("Value returned: " + returnValue);
        else
            System.out.println("No value returned");
    }
}
