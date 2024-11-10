package com.challange.picpay.controller;

import com.challange.picpay.model.dto.TransactionDTO;
import com.challange.picpay.model.entity.transaction.Transaction;
import com.challange.picpay.service.impl.TransactionServiceImpl;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@EnableAutoConfiguration
@Slf4j
class TransactionControllerTest {

    @MockBean
    private TransactionServiceImpl transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    void loadContext(){assertTrue(Boolean.TRUE);}

    @Test
    void createTransaction_shouldReturnCreated() throws Exception{
        TransactionDTO transactionDTO = new TransactionDTO(BigDecimal.TEN, 1L, 2L);
        Transaction mockTransaction = new Transaction();
        when(transactionService.createTransaction(transactionDTO)).thenReturn(mockTransaction);

        ResponseEntity<Transaction> response = transactionController.createTransaction(transactionDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockTransaction, response.getBody());
    }

    @Test
    void createTransaction_shouldReturnUnprocessableEntity_whenTransactionDtoIsInvalid() throws Exception {
        ResponseEntity<Transaction> response = transactionController.createTransaction(null);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
    }
}