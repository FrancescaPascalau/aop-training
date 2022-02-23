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

@Aspect
@Component
public class LogMethodInfoService {

    /**
     * Logging the method name, class name and params/arg passed for any method annotated with @LogMethodInfo
     * Also log the return of the object
     * Use INFO log level for default logs and WARN for exceptions.
     */
    @Around("@annotation(com.francesca.pascalau.aspect.annotation.LogMethodInfo)")
    public Object logMethodInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        var methodSignature = (MethodSignature) joinPoint.getSignature();
        var className = methodSignature.getDeclaringTypeName();
        var methodName = methodSignature.getName();

        System.out.println("\nExecuting Method: " + methodName + "() from Class: " + className + " with Args: ");

        Object[] signatureArgs = joinPoint.getArgs();
        for (int i = 0; i < signatureArgs.length; i++) {
            Object signatureArg = signatureArgs[i];
            System.out.println(++i + ". " + signatureArg + "\n");
        }

        var method = methodSignature.getMethod();
        var annotation = method.getAnnotation(LogMethodInfo.class);
        Logger logger = LoggerFactory.getLogger(method.getDeclaringClass());

        try {
            var returnValue = joinPoint.proceed();
            log(logger, annotation.defaultLogging(), getSuccessfulMessage(returnValue));
            return returnValue;
        } catch (Exception e) {
            log(logger, annotation.exceptionLogging(), getExceptionMessage(methodName, e));
            return null;
        }
    }

    private String getSuccessfulMessage(Object returnValue) {
        return returnValue != null ? "Value returned: " + returnValue : "No value returned";
    }

    private String getExceptionMessage(String methodName, Exception e) {
        return "An exception occurred during execution of " + methodName + " with message: " + e.getMessage();
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
