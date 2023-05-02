package com.example.codechallenge.controller;

import com.example.codechallenge.entity.Trade;
import com.example.codechallenge.orchestrator.DefaultOrchestrator;
import com.example.codechallenge.service.TradeIngestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.JAXBException;
import java.io.IOException;


@RestController
@RequestMapping(path="/rest/api")
public class TradeMessageController {

    @Autowired
    DefaultOrchestrator orchestrator;

    @Autowired
    private TradeIngestionService ingestionService;

    @GetMapping(value = "/get/{id}"/*, produces = "application/json"*/)
        public ResponseEntity<String> getById(@PathVariable(value ="id") String id) throws JsonProcessingException {
          Trade msg = ingestionService.getById(id);
          System.out.println("Message " + msg);
           return new ResponseEntity<String>(ingestionService.getById(id).toString(), HttpStatus.OK);
        }

    @PostMapping("/ingest")
    public ResponseEntity<String> ingest(@RequestBody String str) throws JAXBException, IOException {
        try {
            orchestrator.orchestrate(str);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Trade processed successfully!", HttpStatus.OK);
    }


    @GetMapping("/stop/ingestion")
    public void stopIngestion(){
        ingestionService.stopIngestion();
    }

    @PostMapping("/start/ingestion")
    public void startIngestion(@RequestBody String str){
        ingestionService.startIngest(str);
    }
}

