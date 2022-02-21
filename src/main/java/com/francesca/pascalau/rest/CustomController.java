package com.francesca.pascalau.rest;

import com.francesca.pascalau.domain.model.CustomParams;
import com.francesca.pascalau.domain.model.CustomResponse;
import com.francesca.pascalau.domain.service.CustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("aop/v1")
@RequiredArgsConstructor
public class CustomController {

    private final CustomService customService;

    @PostMapping("/create")
    public void createRequest() {
        var contract = customService.create(CustomParams.builder().build());
    }

    @GetMapping("/find")
    public ResponseEntity<CustomResponse> getResponse() {
        CustomResponse response = customService.find();

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }
}
