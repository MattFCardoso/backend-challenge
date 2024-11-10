package com.challange.picpay.service.impl;

import com.challange.picpay.model.entity.user.User;
import com.challange.picpay.model.dto.NotificationDTO;
import com.challange.picpay.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);


       ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequest, String.class);

       if (!notificationResponse.getStatusCode().is2xxSuccessful()){
           System.out.println("notification error");
           log.error("Notification error");
           throw new Exception("Notification service out of work");
       }
    }
}
