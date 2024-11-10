package com.challange.picpay.model.dto;

import com.challange.picpay.model.entity.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType){
}
