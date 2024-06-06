package com.example.apiWithDb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Builder
@Data
public class ErrorDto {
    private Integer statusCode;
    private String message;
}
