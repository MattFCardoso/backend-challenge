package com.challange.picpay.service;

import com.challange.picpay.domain.user.User;
import com.challange.picpay.domain.user.UserType;
import com.challange.picpay.dto.UserDTO;
import com.challange.picpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository repository;
//todo: create custom exceptions

    public void validateTransaction (User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Merchants cannot make transactions");
        }
        if (sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Insuficient amount");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public User createUser(UserDTO userDTO) {
        User newUser = new User(userDTO);
        this.saveUser(newUser);
        return newUser;
    }

    public void saveUser(User user){
        this.repository.save(user);
    }

    public List<User> getAllUsers() {
        return this.repository.findAll();
    }
}
