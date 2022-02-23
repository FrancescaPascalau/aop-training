package com.francesca.pascalau.domain.service;

import com.francesca.pascalau.aspect.annotation.LogExecutionTime;
import com.francesca.pascalau.domain.model.CustomParams;
import com.francesca.pascalau.domain.model.CustomRequest;
import com.francesca.pascalau.domain.model.Type;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CustomService {

    @LogExecutionTime
    public CustomRequest create(CustomParams params) {
        sleep(2000);

        return CustomRequest.builder()
                .id(UUID.randomUUID())
                .type(Type.valueOf(params.getType()))
                .message(params.getMessage())
                .createdAt(LocalDateTime.now())
                .build();
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
