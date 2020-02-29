package com.zenhomes.consumptionreport.repositories;

import com.zenhomes.consumptionreport.models.Reading;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ReadingRepository extends CrudRepository<Reading, Integer> {
    //Last insert for the id
    Optional<Reading> findTopByVillageIdOrderByTimeDesc(Integer villageId);

    //first insert since the beginning of the time window for the id
    Optional<Reading> findTopByVillageIdAndTimeAfterOrderByTimeAsc(Integer villageId, Date time);
}
