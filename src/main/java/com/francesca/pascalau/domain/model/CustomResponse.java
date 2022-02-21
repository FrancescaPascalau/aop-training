package com.francesca.pascalau.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CustomResponse {

    private UUID id;
    private String message;
    private LocalDateTime issuedAt;
}
