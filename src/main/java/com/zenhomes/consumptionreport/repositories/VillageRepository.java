package com.zenhomes.consumptionreport.repositories;

import com.zenhomes.consumptionreport.models.Village;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VillageRepository extends CrudRepository<Village, Integer> {
}
