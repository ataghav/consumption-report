package com.zenhomes.consumptionreport.services;

import com.zenhomes.consumptionreport.dtos.ReadingDTO;
import com.zenhomes.consumptionreport.dtos.VillageInfoDTO;
import com.zenhomes.consumptionreport.models.Reading;
import com.zenhomes.consumptionreport.models.Village;
import com.zenhomes.consumptionreport.repositories.ExternalServicesRepository;
import com.zenhomes.consumptionreport.repositories.ReadingRepository;
import com.zenhomes.consumptionreport.repositories.VillageRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class ReadingServiceImpl implements ReadingService {

    private ExternalServicesRepository externalServicesRepository;
    private ReadingRepository readingRepository;
    private VillageRepository villageRepository;

    //    @Autowired
    public ReadingServiceImpl(ExternalServicesRepository eRepository,
                              ReadingRepository rRepository,
                              VillageRepository vRepository) {
        this.externalServicesRepository = eRepository;
        this.readingRepository = rRepository;
        this.villageRepository = vRepository;
    }

    @Override
    public Integer handleNewReading(ReadingDTO ReadingDTO) {
        var villageInfoDTO = externalServicesRepository.lookupVillage(ReadingDTO.getCounter_id());

        var reading = saveReading(ReadingDTO, villageInfoDTO);

        if (!villageRepository.existsById(reading.getVillageId())) {
            saveVillage(reading.getVillageId(), reading.getVillageName());
        }
        return reading.getId();
    }

    private Reading saveReading(ReadingDTO ReadingDTO, VillageInfoDTO villageInfoDTO) {
        var reading = new Reading();

        reading.setCounterId(ReadingDTO.getCounter_id());
        reading.setAmount(ReadingDTO.getAmount());
        reading.setTime(Date.from(Instant.now()));
        reading.setVillageId(villageInfoDTO.getVillage().getId());
        reading.setVillageName(villageInfoDTO.getVillage().getName());
        var lastReading = readingRepository.findTopByVillageIdOrderByTimeDesc(villageInfoDTO.getVillage().getId());
        var difference = lastReading.isPresent() ? (ReadingDTO.getAmount() - lastReading.get().getAmount()) : 0d;
        reading.setDifference(difference);

        return readingRepository.save(reading);
    }

    private void saveVillage(Integer id, String name) {
        var village = new Village();
        village.setId(id);
        village.setName(name);
        villageRepository.save(village);
    }
}
