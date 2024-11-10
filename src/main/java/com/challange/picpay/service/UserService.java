package com.challange.picpay.service;

import com.challange.picpay.domain.user.User;
import com.challange.picpay.dto.UserDTO;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    void validateTransaction (User sender, BigDecimal amount) throws Exception;
    User createUser(UserDTO userDTO);
    List<User> getAllUsers();
    void saveUser(User user);
    User findUserById(Long id) throws Exception;
}
