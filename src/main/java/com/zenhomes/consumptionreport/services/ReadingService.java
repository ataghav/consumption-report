package com.zenhomes.consumptionreport.services;

import com.zenhomes.consumptionreport.dtos.ReadingDTO;

public interface ReadingService {
    Integer handleNewReading(ReadingDTO ReadingDTO);
}
