package com.challange.picpay.controller;

import com.challange.picpay.domain.user.User;
import com.challange.picpay.dto.UserDTO;
import com.challange.picpay.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(("/users"))
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
        User newUser = userService.createUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList =  this.userService.getAllUsers();

        if (Objects.isNull(userList)){
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

}
