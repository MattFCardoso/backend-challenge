package com.challange.picpay.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@EnableAutoConfiguration
class AppConfigTest {


    @Test
    void restTemplateBeanShouldBeCreatedSuccessfully() {
        AppConfig appConfig = new AppConfig();
        RestTemplate restTemplate = appConfig.restTemplate();
        assertNotNull(restTemplate);
        assertTrue(restTemplate instanceof RestTemplate);
    }

    @Test
    void restTemplateBeanShouldSupportAllHttpMethods() {
        AppConfig appConfig = new AppConfig();
        RestTemplate restTemplate = appConfig.restTemplate();
        assertNotNull(restTemplate);

        assertTrue(restTemplate.getClass().getDeclaredMethods().length > 0);

        String[] httpMethods = {"GET", "POST", "PUT", "DELETE", "PATCH", "HEAD", "OPTIONS"};
        for (String method : httpMethods) {
            assertTrue(Arrays.stream(restTemplate.getClass().getDeclaredMethods())
                            .anyMatch(m -> m.getName().toUpperCase().contains(method)),
                    "RestTemplate should support " + method + " method");
        }
    }

}