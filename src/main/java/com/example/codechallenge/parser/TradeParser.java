package com.example.codechallenge.parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;

import com.example.codechallenge.entity.Trade;
import org.apache.commons.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TradeParser {

    Logger log = LoggerFactory.getLogger(TradeParser.class);

    public Trade parser(String xml) throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(Trade.class);
        Unmarshaller um = jc.createUnmarshaller();

        InputStream in = IOUtils.toInputStream(xml.trim());
        Trade trade = (Trade) um.unmarshal(in);

        log.info("Message parsed successfully. Trade :: {}" , trade);

        return trade;

    }
}
