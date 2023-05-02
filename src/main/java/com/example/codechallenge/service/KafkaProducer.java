package com.example.codechallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaSendCallback kafkaSendCallback;

    public void sendMessageToTopic(String message) {

        kafkaSendCallback.sendMessageWithCallback(message);
    }
}
