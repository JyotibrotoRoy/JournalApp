package com.jyotibroto.journalApp.service;

import com.jyotibroto.journalApp.model.SentimentData;
import com.jyotibroto.journalApp.scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
public class UserSchedulerTest {

    @Autowired
    private UserScheduler userScheduler;

    @Autowired
    private KafkaTemplate<String, SentimentData> kafkaTemplate;

    @Test
    public void testFetchUserAndSendSaMail() {
        userScheduler.fetchUserAndSendSaMail();
    }

    @Test
    public void testDirectKafkaSend() {
        kafkaTemplate.send("weekly-sentiments", "test-key",
                new SentimentData("test@example.com", "Hello Kafka from test"));
    }

    @Test
    public void testFetchUserAndSendSaMail_BypassDB() {
        SentimentData data = new SentimentData("bypass@example.com", "This is a bypass test");
        kafkaTemplate.send("weekly-sentiments", data.getEmail(), data);
    }

    @Test
    public void testDirectKafkaSendEmail() throws InterruptedException{
        kafkaTemplate.send("weekly-sentiments", "test-key",
                new SentimentData("roylil675@gmail.com", "Hello from Kafka!"));
        Thread.sleep(5000);
    }

}
