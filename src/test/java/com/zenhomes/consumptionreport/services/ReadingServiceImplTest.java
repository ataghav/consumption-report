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
//    @Test
//    void newReading() {
//    }

//    @MockBean
//    ExternalServicesRepository externalServicesRepository;
//
//    @MockBean
//    ReadingRepository readingRepository;
//
//    @MockBean
//    VillageRepository villageRepository;
//
//    private ReadingServiceImpl service = new ReadingServiceImpl(externalServicesRepository,
//            readingRepository, villageRepository);
//
//    @Test
//    public void newReadingMakesExternalServiceCall() {
//        Mockito.when(externalServicesRepository.lookupVillage(1)).thenReturn(new VillageInfoDTO(1,new VillageLookupDTO(100,"Villarriba")));
//        Mockito.when(villageRepository.existsById(1)).thenReturn(false);
//
//        service.newReading(new CallBackDTO(1,100.123));
//
//        Mockito.verify(villageRepository, Mockito.times(1)).save(new Village());
//    }
//
//    @Test
//    public void newReadingPersistsNewVillages() {
//
//    }
}
