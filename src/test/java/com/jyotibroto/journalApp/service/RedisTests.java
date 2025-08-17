package com.jyotibroto.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {
    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    void testSendMail() {
        redisTemplate.opsForValue().set("lol","com");
        System.out.println("✅ Key written to Redis Cloud!");
    }
}
