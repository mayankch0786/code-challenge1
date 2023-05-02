package com.example.codechallenge.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandler {

    Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    public void handle(Exception e){

        if(e instanceof TradeValidationException){
            log.error("Trade message is invalid, hence not reprocessing.");
            throw new TradeValidationException("Trade message is invalid, hence not reprocessing.");
        } else if(e instanceof  ProcessingException){
            log.error("Processing exception occurred, hence reprocessing.");
            throw new ProcessingException("Processing exception occurred, hence reprocessing.");
        } else {
            log.error(e.getCause().getMessage());
            throw new RuntimeException(e);
        }
    }
}
