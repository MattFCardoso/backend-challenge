package com.challange.picpay.service;

import com.challange.picpay.domain.user.User;

public interface NotificationService {

    void sendNotification(User user, String message) throws Exception;
}
