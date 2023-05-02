package com.example.codechallenge.service;

import com.example.codechallenge.orchestrator.DefaultOrchestrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListner {

    @Autowired
    private DefaultOrchestrator orchestrator;

    Logger log = LoggerFactory.getLogger(KafkaListner.class);

    @KafkaListener(topics = "test-topic-1", groupId = "codedecode-group")
    public void listen(String messageReceived) {
        try {
            orchestrator.orchestrate(messageReceived);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
