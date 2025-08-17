package com.jyotibroto.journalApp.service;

import com.jyotibroto.journalApp.Repository.UserRepositoryImpl;
import com.jyotibroto.journalApp.Service.EmailService;
import com.jyotibroto.journalApp.Service.UserService;
import com.jyotibroto.journalApp.scheduler.UserScheduler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailserviceTests {

    @Autowired
    private UserScheduler userScheduler;

    @Test
    public void sendMail() {
        userScheduler.fetchUserAndSendSaMail();
    }
}
