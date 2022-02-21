package com.francesca.pascalau.domain.converter;

import com.francesca.pascalau.domain.model.CustomParams;
import com.francesca.pascalau.domain.model.CustomRequest;
import com.francesca.pascalau.domain.model.Type;

import java.time.LocalDateTime;
import java.util.UUID;

public class CustomConverter {

    public CustomRequest convertParams(CustomParams params) {
        return CustomRequest.builder()
                .id(UUID.randomUUID())
                .createdAt(LocalDateTime.now())
                .message(params.getMessage())
                .type(Type.valueOf(params.getType()))
                .build();
    }
}
