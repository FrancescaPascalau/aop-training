package com.francesca.pascalau.aspect.annotation;

import org.springframework.boot.logging.LogLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogMethodInfo {

    /**
     * Not really necessary, but it can be defined at the interface level too
     */
    LogLevel defaultLogging() default LogLevel.INFO;

    LogLevel exceptionLogging() default LogLevel.WARN;
}
