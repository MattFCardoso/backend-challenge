package com.challange.picpay.service;

import com.challange.picpay.model.entity.user.User;

public interface NotificationService {

    void sendNotification(User user, String message) throws Exception;
}
