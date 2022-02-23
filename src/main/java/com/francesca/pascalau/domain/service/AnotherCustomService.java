package com.francesca.pascalau.domain.service;

import com.francesca.pascalau.aspect.annotation.LogExecutionTime;
import com.francesca.pascalau.aspect.annotation.LogMethodInfo;
import com.francesca.pascalau.domain.model.CustomParams;
import com.francesca.pascalau.domain.model.CustomRequest;
import com.francesca.pascalau.domain.model.CustomResponse;
import com.francesca.pascalau.domain.model.Type;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class AnotherCustomService {

    @LogExecutionTime
    public CustomRequest create(CustomParams params) {
        return CustomRequest.builder()
                .id(UUID.randomUUID())
                .type(Type.valueOf(params.getType()))
                .message(params.getMessage())
                .createdAt(LocalDateTime.now())
                .build();
    }

    @LogMethodInfo
    public CustomResponse create(CustomRequest request) {
        return CustomResponse.builder()
                .id(UUID.randomUUID())
                .message(request.getMessage())
                .issuedAt(request.getCreatedAt())
                .build();
    }

    @LogMethodInfo
    public void failOnPurpose() throws Exception {
        throw new Exception("Oh no! This is an exception!");
    }
}
