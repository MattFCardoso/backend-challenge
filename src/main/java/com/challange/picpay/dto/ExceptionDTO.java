package com.challange.picpay.dto;

import org.springframework.http.HttpStatus;

public record ExceptionDTO (String message, HttpStatus status, String statusCode){}
