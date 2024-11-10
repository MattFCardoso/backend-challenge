package com.challange.picpay.service.impl;

import com.challange.picpay.model.dto.UserDTO;
import com.challange.picpay.model.entity.user.User;
import com.challange.picpay.model.entity.user.UserType;
import com.challange.picpay.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static java.math.BigDecimal.ONE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@EnableAutoConfiguration
class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void loadContext() {
        assertTrue(Boolean.TRUE);
    }

    @Test
    void createUser_shouldCreateNewUserSuccessfully() {
        UserDTO userDTO = new UserDTO("John", "Doe", "123456789",
                BigDecimal.valueOf(100), "john@example.com", "password", UserType.COMMON);
        User expectedUser = new User(userDTO);
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        User createdUser = userService.createUser(userDTO);

        assertNotNull(createdUser);
        assertEquals(expectedUser.getFirstName(), createdUser.getFirstName());
        assertEquals(expectedUser.getLastName(), createdUser.getLastName());
        assertEquals(expectedUser.getDocument(), createdUser.getDocument());
        assertEquals(expectedUser.getBalance(), createdUser.getBalance());
        assertEquals(expectedUser.getEmail(), createdUser.getEmail());
        assertEquals(expectedUser.getUserType(), createdUser.getUserType());

        verify(userRepository, times(1)).save(any(User.class));
    }

}