package com.example.codechallenge.service;

import com.example.codechallenge.entity.Trade;
import com.example.codechallenge.repository.TradeRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeIngestionService {
    @Autowired
    private KafkaSendCallback kafkaSendCallback;

    @Autowired
    private TradeRespository repository;

    Logger log = LoggerFactory.getLogger(TradeIngestionService.class);

    private boolean isStart = false;

    public void startIngest(String xml){
        int i = 1;
        isStart = true;
        String xmlMsg;
        while(isStart){
            xmlMsg = xml.replace("test", "test_" + i);
            log.info("Sending message : {}", xmlMsg);
            kafkaSendCallback.sendMessageWithCallback(xmlMsg);
            i++;
            try {
                Thread.currentThread().sleep(1000);
            } catch (Exception e){
                log.error("Interrupted exception..");
            }
        }
    }

    public void stopIngestion(){
        log.info("Ingestion stop signal received.");
        log.info("Stopping the ingestion");
        isStart = false;
    }

    public Trade getById(String id){
        return repository.getById(id);
    }
}
