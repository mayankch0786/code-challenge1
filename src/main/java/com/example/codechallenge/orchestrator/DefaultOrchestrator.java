package com.example.codechallenge.orchestrator;

import com.example.codechallenge.entity.Trade;
import com.example.codechallenge.exception.ExceptionHandler;
import com.example.codechallenge.parser.TradeParser;
import com.example.codechallenge.processor.TradeProcessor;
import com.example.codechallenge.validator.TradeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultOrchestrator {

    @Autowired
    TradeParser parser;
    @Autowired
    TradeValidator validator;
    @Autowired
    TradeProcessor processor;

    @Autowired
    ExceptionHandler handler;

    public void orchestrate(String xml){
        try {
            Trade trade = parser.parser(xml);
            validator.tradeValidator(trade);
            processor.process(trade);

        } catch(Exception e){
            handler.handle(e);
        }

    }

}
