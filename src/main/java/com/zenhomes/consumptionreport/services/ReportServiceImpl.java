package com.zenhomes.consumptionreport.services;

import com.zenhomes.consumptionreport.dtos.VillageReportDTO;
import com.zenhomes.consumptionreport.models.Reading;
import com.zenhomes.consumptionreport.models.Village;
import com.zenhomes.consumptionreport.repositories.ReadingRepository;
import com.zenhomes.consumptionreport.repositories.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReadingRepository readingRepository;

    @Autowired
    private VillageRepository villageRepository;

    @Override
    public Iterable<Reading> findAllReadings() {
        return readingRepository.findAll();
    }

    @Override
    public Optional<Reading> findReadingById(Integer id) {
        return readingRepository.findById(id);
    }

    @Override
    public Iterable<Village> findAllVillages() {
        return villageRepository.findAll();
    }

    @Override
    public Optional<Village> findVillageById(Integer id) {
        return villageRepository.findById(id);
    }

    public List<VillageReportDTO> getReport(Integer duration) {
        var start = Date.from(Instant.now().minus(duration, ChronoUnit.HOURS));
        return StreamSupport.stream(villageRepository.findAll().spliterator(), false)
                .map(v -> new VillageReportDTO(v.getName(), calculateConsumption(v.getId(), start)))
                .collect(Collectors.toList());
    }

    private Double calculateConsumption(Integer id, Date start) {
        var lastReading = readingRepository.findTopByVillageIdOrderByTimeDesc(id);
        var firstReading = readingRepository.findTopByVillageIdAndTimeAfterOrderByTimeAsc(id, start);
        if (lastReading.isEmpty() || firstReading.isEmpty()) {
            return 0d;
        }
        return lastReading.get().getAmount() - firstReading.get().getAmount();
    }
}
