package com.example.codechallenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "trade")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trade")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Trade {

    /*
    **  No need of @XmlElement as xml and pojo attribute names are same
     */

    //@XmlElement(name = "contractNumber")
    @Id
    private String contractNumber;

    //@XmlElement(name = "tradeVersion")
    private int tradeVersion;

    //@XmlElement(name = "status")
    private String status;

    //@XmlElement(name = "saleAmount")
    private int saleAmount;

    //@XmlElement(name = "saleCurrency")
    private String saleCurrency;

    //@XmlElement(name = "assetClass")
    private String assetClass;

}
