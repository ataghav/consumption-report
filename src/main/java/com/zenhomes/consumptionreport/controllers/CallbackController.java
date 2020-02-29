package com.zenhomes.consumptionreport.controllers;

import com.zenhomes.consumptionreport.dtos.ReadingDTO;
import com.zenhomes.consumptionreport.services.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CallbackController {
    private ReadingService readingService;

    @Autowired
    public CallbackController(ReadingService service) {
        this.readingService = service;
    }

    @RequestMapping(value = "/counter_callback", method = RequestMethod.POST)
    public Integer addReading(@Valid @RequestBody ReadingDTO ReadingDTO) {
        return readingService.handleNewReading(ReadingDTO);
    }
}
