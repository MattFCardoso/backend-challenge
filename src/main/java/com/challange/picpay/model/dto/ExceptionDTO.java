package com.challange.picpay.model.dto;

import org.springframework.http.HttpStatus;

public record ExceptionDTO (String message, HttpStatus status, String statusCode){}
