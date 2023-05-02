package com.example.codechallenge.repository;

import com.example.codechallenge.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRespository extends JpaRepository<Trade, String> {

}
