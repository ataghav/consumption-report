package com.zenhomes.consumptionreport.services;

import com.zenhomes.consumptionreport.dtos.VillageReportDTO;
import com.zenhomes.consumptionreport.models.Reading;
import com.zenhomes.consumptionreport.models.Village;

import java.util.List;
import java.util.Optional;

public interface ReportService {
    Iterable<Reading> findAllReadings();

    Optional<Reading> findReadingById(Integer id);

    Iterable<Village> findAllVillages();

    Optional<Village> findVillageById(Integer id);

    List<VillageReportDTO> getReport(Integer durationHours);
}
