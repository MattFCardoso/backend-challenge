package com.challange.picpay.service.impl;

import com.challange.picpay.model.entity.user.User;
import com.challange.picpay.model.entity.user.UserType;
import com.challange.picpay.model.dto.UserDTO;
import com.challange.picpay.repository.UserRepository;
import com.challange.picpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public void validateTransaction (User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Merchants cannot make transactions");
        }
        if (sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Insuficient amount");
        }
    }

    @Override
    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("User not found"));
    }

    @Override
    public User createUser(UserDTO userDTO) {
        User newUser = new User(userDTO);
        this.saveUser(newUser);
        return newUser;
    }

    @Override
    public void saveUser(User user){
        this.repository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.repository.findAll();
    }
}
