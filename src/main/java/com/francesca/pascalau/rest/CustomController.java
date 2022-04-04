package com.francesca.pascalau.rest;

import com.francesca.pascalau.domain.model.CustomParams;
import com.francesca.pascalau.domain.model.CustomRequest;
import com.francesca.pascalau.domain.model.CustomResponse;
import com.francesca.pascalau.domain.model.Type;
import com.francesca.pascalau.domain.service.AnotherCustomService;
import com.francesca.pascalau.domain.service.CustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/aop-training")
@RequiredArgsConstructor
public class CustomController {

    private final CustomService customService;
    private final AnotherCustomService anotherCustomService;

    @PostMapping("/create/with-log")
    public ResponseEntity<CustomRequest> create(@RequestBody CustomParams params) {
        var customRequest = customService.logExecutionTime(
                CustomParams.builder()
                        .type(params.getType())
                        .message(params.getMessage())
                        .build());

        return new ResponseEntity<>(customRequest, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/create/without-log")
    public ResponseEntity<CustomRequest> createWithoutLog(@RequestBody CustomParams params) {
        var customRequest = anotherCustomService.logExecutionTime(
                CustomParams.builder()
                        .type(params.getType())
                        .message(params.getMessage())
                        .build());

        return new ResponseEntity<>(customRequest, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/success")
    public ResponseEntity<CustomResponse> getResponse() {
        var customResponse = anotherCustomService.logInfo(
                CustomRequest.builder()
                        .id(UUID.randomUUID())
                        .createdAt(LocalDateTime.now())
                        .type(Type.LOW)
                        .message("Hello World!")
                        .build());

        return new ResponseEntity<>(customResponse, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/fail")
    public void getException() throws Exception {
        anotherCustomService.failOnPurpose();
    }
}
