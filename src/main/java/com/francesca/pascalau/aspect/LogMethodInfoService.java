package com.francesca.pascalau.aspect;

import com.francesca.pascalau.aspect.annotation.LogMethodInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Locale;

@Aspect
@Component
public class LogMethodInfoService {

    @Around("@annotation(com.francesca.pascalau.aspect.annotation.LogMethodInfo)")
    public Object logMethodInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        var methodName = joinPoint.getSignature().getName();
        var className = joinPoint.getSignature().getDeclaringTypeName();

        System.out.println("\nExecuting Method: " + methodName + "() from Class: " + className + " with Args: ");

        Object[] signatureArgs = joinPoint.getArgs();
        for (int i = 0; i < signatureArgs.length; i++) {
            Object signatureArg = signatureArgs[i];
            System.out.println(++i + ". " + signatureArg);
        }

        var methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        var annotation = method.getAnnotation(LogMethodInfo.class);
        Logger logger = LoggerFactory.getLogger(method.getDeclaringClass());

        try {
            var returnValue = joinPoint.proceed();
            log(logger, annotation.defaultLogging(), returnValue != null ? "Value returned: " + returnValue : "No value returned");
            return returnValue;
        } catch (Exception e) {
            log(logger, annotation.exceptionLogging(), "An exception occurred during execution of " + methodName.toUpperCase(Locale.ROOT));
            return null;
        }
    }

    static void log(Logger logger, LogLevel level, String message) {
        switch (level) {
            case DEBUG -> logger.debug(message);
            case TRACE -> logger.trace(message);
            case WARN -> logger.warn(message);
            case ERROR, FATAL -> logger.error(message);
            default -> logger.info(message);
        }
    }
}
