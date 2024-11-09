package com.challange.picpay.controller;

import com.challange.picpay.domain.transaction.Transaction;
import com.challange.picpay.domain.user.User;
import com.challange.picpay.dto.TransactionDTO;
import com.challange.picpay.dto.UserDTO;
import com.challange.picpay.service.TransactionService;
import com.challange.picpay.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(("/transactions"))
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        Transaction newTransaction = this.transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }

}
