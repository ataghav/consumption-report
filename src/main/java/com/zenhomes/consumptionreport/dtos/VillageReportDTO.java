package com.zenhomes.consumptionreport.dtos;

import lombok.Data;

@Data
public class VillageReportDTO {
    private String village_name;
    private Double consumption;

    public VillageReportDTO(String village_name, Double consumption) {
        this.village_name = village_name;
        this.consumption = cutFraction(consumption);
    }

    private Double cutFraction(Double number) {
        return Math.floor(number * 1000) / 1000;
    }
}
