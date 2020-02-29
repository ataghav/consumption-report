package com.zenhomes.consumptionreport.repositories;

import com.zenhomes.consumptionreport.dtos.VillageInfoDTO;

public interface ExternalServicesRepository {
    VillageInfoDTO lookupVillage(Integer villageId);
}
