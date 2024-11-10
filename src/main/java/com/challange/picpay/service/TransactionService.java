package com.challange.picpay.service;

import com.challange.picpay.model.entity.transaction.Transaction;
import com.challange.picpay.model.entity.user.User;
import com.challange.picpay.model.dto.TransactionDTO;

import java.math.BigDecimal;

public interface TransactionService {

    Transaction createTransaction(TransactionDTO transaction) throws Exception;
    boolean authorizeTransaction(User sender, BigDecimal value);
}
