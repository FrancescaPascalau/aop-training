package com.francesca.pascalau.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CustomRequest {

    private UUID id;
    private Type type;
    private String message;
    private LocalDateTime createdAt;
}
