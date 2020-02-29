package com.zenhomes.consumptionreport.controllers;

import com.zenhomes.consumptionreport.dtos.VillageReportDTO;
import com.zenhomes.consumptionreport.models.Reading;
import com.zenhomes.consumptionreport.models.Village;
import com.zenhomes.consumptionreport.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Hashtable;

@RestController
public class ReportController {

    private ReportService reportService;
    private Hashtable<String, Integer> reportDuration;

    @Autowired
    public ReportController(ReportService service, Hashtable<String, Integer> hashtable) {
        this.reportService = service;
        this.reportDuration = hashtable;
    }

    @RequestMapping("/consumption_report")
    public Iterable<VillageReportDTO> getReport(@RequestParam(value = "duration") String duration) {
        if (reportDuration.containsKey(duration)) {
            return reportService.getReport(reportDuration.get(duration));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Duration");
    }

    @RequestMapping("/readings")
    public Iterable<Reading> getAllReadings() {
        return reportService.findAllReadings();
    }

    @RequestMapping("/readings/{readingId}")
    public Reading getReadingById(@PathVariable("readingId") Integer readingId) {
        var result = reportService.findReadingById(readingId);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/villages")
    public Iterable<Village> getAllVillages() {
        return reportService.findAllVillages();
    }


    @RequestMapping("/villages/{villageId}")
    public Village getVillageById(@PathVariable("villageId") Integer villageId) {
        var result = reportService.findVillageById(villageId);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}