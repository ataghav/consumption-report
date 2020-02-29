package com.zenhomes.consumptionreport.services;

import com.zenhomes.consumptionreport.repositories.ExternalServicesRepository;
import com.zenhomes.consumptionreport.repositories.ReadingRepository;
import com.zenhomes.consumptionreport.repositories.VillageRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = {ExternalServicesRepository.class, ReadingRepository.class, VillageRepository.class})
public class ReadingServiceImplTest {

}
