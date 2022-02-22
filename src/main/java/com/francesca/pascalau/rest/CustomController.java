package com.francesca.pascalau.rest;

import com.francesca.pascalau.domain.model.CustomParams;
import com.francesca.pascalau.domain.model.CustomRequest;
import com.francesca.pascalau.domain.service.CustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aop-training")
@RequiredArgsConstructor
public class CustomController {

    private final CustomService customService;

    @PostMapping("/create")
    public ResponseEntity<CustomRequest> create(@RequestBody CustomParams params) {
        var customRequest = customService.create(
                CustomParams.builder()
                        .type(params.getType())
                        .message(params.getMessage())
                        .build());

        return new ResponseEntity<>(customRequest, new HttpHeaders(), HttpStatus.OK);
    }
}
