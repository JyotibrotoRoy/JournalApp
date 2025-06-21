package com.jyotibroto.journalApp.service;

import com.jyotibroto.journalApp.Repository.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void testNewUser() {
        Assertions.assertNotNull(userRepository.getUsersforSA());
    }
}
