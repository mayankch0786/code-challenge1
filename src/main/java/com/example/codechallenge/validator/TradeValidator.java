package com.example.codechallenge.validator;

import com.example.codechallenge.entity.Trade;
import com.example.codechallenge.exception.TradeValidationException;
import com.example.codechallenge.repository.TradeRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class TradeValidator {

    @Autowired
    private TradeRespository repository;

    Logger log = LoggerFactory.getLogger(TradeValidator.class);

    public void tradeValidator(Trade message){

    Optional optional = repository.findById(message.getContractNumber());
        Trade tradeInDb = optional.isPresent() ? (Trade) optional.get() : null;
        log.info("Fetching trade from DB for Contract Number : {} ", message.getContractNumber());
        if(tradeInDb != null) {
            log.info("Trade found in DB. Trade : {}", tradeInDb);
            if (tradeInDb.getTradeVersion() >= message.getTradeVersion()) {
                log.error("Trade version should be higher to process the trade.");
                throw new TradeValidationException("Trade version should be higher to process the trade.");
            }
            log.info("No higher version present in DB for contact number : {}", message.getContractNumber());

        }
    }
}
