package com.francesca.pascalau.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomParams {

    private String type;
    private String message;
}
