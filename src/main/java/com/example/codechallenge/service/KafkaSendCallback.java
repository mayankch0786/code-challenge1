package com.example.codechallenge.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
@Component
public class KafkaSendCallback {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    Logger LOG = LoggerFactory.getLogger(KafkaSendCallback.class);

    void sendMessageWithCallback(String message) {
        ListenableFuture<SendResult<String, String>> future =
                // use key {i.e. 2nd argument below } to send message to a particular partition in a sequence
                // if the key is null then message will be sent to partition in round robin fashion
                kafkaTemplate.send("test-topic-1","1", message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                LOG.info("Message [{}] delivered with partition {} and offset {}",
                        message,
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOG.warn("Unable to deliver message [{}]. {}",
                        message,
                        ex.getMessage());
            }
        });
    }
}

