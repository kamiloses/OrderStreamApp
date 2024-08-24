package com.group.kamiloses.orderstreamapp.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String timestamp;
    private String path;
    private int status;
    private String error;
    private String message;
}