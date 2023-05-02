package com.example.codechallenge.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    Logger log = LoggerFactory.getLogger(KafkaTopicConfig.class);

    @Bean
    public KafkaAdmin admin()
    {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic1(){
        NewTopic newTopic = TopicBuilder.name("test-topic-1").partitions(3).build();
        log.info("Topic created {}", newTopic.toString());
        return  newTopic;
    }
}
