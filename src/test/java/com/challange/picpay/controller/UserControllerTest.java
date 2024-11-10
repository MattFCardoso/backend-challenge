package com.challange.picpay.controller;

import com.challange.picpay.model.dto.UserDTO;
import com.challange.picpay.model.entity.user.User;
import com.challange.picpay.model.entity.user.UserType;
import com.challange.picpay.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@EnableAutoConfiguration
@Slf4j
class UserControllerTest {

    @MockBean
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    @Test
    void loadContext() {
        assertTrue(Boolean.TRUE);
    }

    @Test
    void createUser_shouldReturnCreatedStatus() {
        UserDTO userDTO = new UserDTO("Test User", "Last name", "123456789",
                BigDecimal.TEN, "test@email.com", null, UserType.COMMON);


        User mockUser = new User(userDTO);
        when(userService.createUser(any(UserDTO.class))).thenReturn(mockUser);

        ResponseEntity<User> response = userController.createUser(userDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockUser, response.getBody());
    }

    @Test
    void createUser_withEmptyUserDTO_shouldReturnBadRequest() {
        ResponseEntity<User> response = userController.createUser(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getAllUsers_shouldReturnSuccess() {
        List<User> userList = Arrays.asList(
                new User(new UserDTO("Test User", "Last name", "123456789",
                        BigDecimal.TEN, "test@email.com", null, UserType.COMMON)),
                new User(new UserDTO("Test User", "Last name", "123459768"
                        , BigDecimal.TEN, "test@email.com", null, UserType.COMMON))
        );

        when(userService.getAllUsers()).thenReturn(userList);
        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }

    @Test
    void getAllUsers_whenUserListIsNull_shouldReturnNoContent() {
        when(userService.getAllUsers()).thenReturn(null);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

}