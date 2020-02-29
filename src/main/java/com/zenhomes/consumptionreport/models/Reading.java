package com.zenhomes.consumptionreport.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Reading {
    @Id
    @GeneratedValue
    private Integer id;
    private Date time;
    private Integer counterId;
    private Integer villageId;
    private String villageName;
    private Double amount;
    private Double difference;

    public Reading() {
    }

    public Reading(Integer id, Date time, Integer counterId, Integer villageId, String villageName, Double amount, Double difference) {
        this.id = id;
        this.time = time;
        this.counterId = counterId;
        this.villageId = villageId;
        this.villageName = villageName;
        this.amount = amount;
        this.difference = difference;
    }
}
