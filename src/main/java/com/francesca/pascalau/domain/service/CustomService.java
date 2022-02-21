package com.francesca.pascalau.domain.service;

import com.francesca.pascalau.domain.model.CustomParams;
import com.francesca.pascalau.domain.model.CustomRequest;
import com.francesca.pascalau.domain.model.CustomResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomService {

    public CustomRequest create(CustomParams params) {
        return CustomRequest.builder().build();
    }

    public CustomResponse find() {
        return CustomResponse.builder().build();
    }
}
