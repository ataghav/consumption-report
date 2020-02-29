package com.zenhomes.consumptionreport.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Village {
    @Id
    private Integer id;
    private String name;

    public Village() {
    }

    public Village(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
