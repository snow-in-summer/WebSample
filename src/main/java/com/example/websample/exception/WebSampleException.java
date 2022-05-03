package com.example.websample.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WebSampleException extends RuntimeException{
    private String errorCode;
    private String message;
}
