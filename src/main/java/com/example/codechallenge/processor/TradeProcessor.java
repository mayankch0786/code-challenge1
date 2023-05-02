package com.example.codechallenge.processor;

import com.example.codechallenge.entity.Trade;
import com.example.codechallenge.exception.ProcessingException;
import com.example.codechallenge.repository.TradeRespository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;


@Service
public class TradeProcessor {
    Logger log = LoggerFactory.getLogger(TradeProcessor.class);

    @Autowired
    private TradeRespository repository;

    public void process(Trade trade){
        try {
                repository.save(trade);
        } catch (Exception e){
            log.error(e.getMessage());
            throw new ProcessingException(e.getMessage());
        }
    }
}
