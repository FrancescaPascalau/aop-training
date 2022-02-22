package com.francesca.pascalau.domain.service;

import com.francesca.pascalau.aspect.annotation.LogMethodInfo;
import com.francesca.pascalau.domain.model.CustomRequest;
import com.francesca.pascalau.domain.model.CustomResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AnotherCustomService {

    @LogMethodInfo
    public CustomResponse create(CustomRequest request) {
        return CustomResponse.builder()
                .id(UUID.randomUUID())
                .message(request.getMessage())
                .issuedAt(request.getCreatedAt())
                .build();
    }
}
