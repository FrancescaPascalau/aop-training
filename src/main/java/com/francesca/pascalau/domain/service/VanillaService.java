package com.francesca.pascalau.domain.service;

import com.francesca.pascalau.domain.model.CustomParams;
import com.francesca.pascalau.domain.model.CustomRequest;
import com.francesca.pascalau.domain.model.CustomResponse;
import com.francesca.pascalau.domain.model.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class VanillaService {

    //Logging the execution time of the method using the System time
    public CustomRequest logExecutionTime(CustomParams params) {
        long startTime = System.currentTimeMillis();

        var request = CustomRequest.builder()
                .id(UUID.randomUUID())
                .type(Type.valueOf(params.getType()))
                .message(params.getMessage())
                .createdAt(LocalDateTime.now())
                .build();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        log.info("Method executed in: " + executionTime + " millis.");

        return request;
    }

    //Logging the method info using the java tools
    public CustomResponse logInfo(CustomRequest request) {
        String methodName = this.getClass().getEnclosingMethod().getName();
        log.info("Current method name: " + methodName);

        Class<?>[] parameterTypes = this.getClass().getEnclosingMethod().getParameterTypes();
        log.info("Method parameter types: ");
        for (Object parameterType : parameterTypes) {
            log.info("\n" + parameterType);
        }

        String className = this.getClass().getSimpleName();
        log.info("Current class name: " + className);

        return CustomResponse.builder()
                .id(UUID.randomUUID())
                .message(request.getMessage())
                .issuedAt(request.getCreatedAt())
                .build();
    }
}
