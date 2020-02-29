package com.zenhomes.consumptionreport.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ReportDTO {
    private List<VillageReportDTO> villages;
}
