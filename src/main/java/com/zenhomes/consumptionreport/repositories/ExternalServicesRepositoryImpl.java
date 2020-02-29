package com.zenhomes.consumptionreport.repositories;

import com.zenhomes.consumptionreport.dtos.VillageInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class ExternalServicesRepositoryImpl implements ExternalServicesRepository {

    private WebClient.Builder webClientBuilder;

    @Autowired
    public ExternalServicesRepositoryImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public VillageInfoDTO lookupVillage(Integer villageId) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri("https://europe-west2-zenhomes-development-project.cloudfunctions.net/counters/" + villageId)
                    .retrieve()
                    .bodyToMono(VillageInfoDTO.class)
                    .block();
        } catch (Exception ex) {
            System.out.println("Network Connection Failure happened");
            return null;
        }
    }
}
