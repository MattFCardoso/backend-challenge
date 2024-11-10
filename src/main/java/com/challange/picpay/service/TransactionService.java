package com.challange.picpay.service;

import com.challange.picpay.domain.transaction.Transaction;
import com.challange.picpay.domain.user.User;
import com.challange.picpay.dto.TransactionDTO;

import java.math.BigDecimal;

public interface TransactionService {

    Transaction createTransaction(TransactionDTO transaction) throws Exception;
    boolean authorizeTransaction(User sender, BigDecimal value);
}
