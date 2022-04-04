package com.francesca.pascalau.domain.service;

import com.francesca.pascalau.domain.model.CustomParams;
import com.francesca.pascalau.domain.model.CustomRequest;
import com.francesca.pascalau.domain.model.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class VanillaService {

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
}
