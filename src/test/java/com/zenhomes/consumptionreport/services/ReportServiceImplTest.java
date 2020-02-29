package com.zenhomes.consumptionreport.services;

import com.zenhomes.consumptionreport.dtos.VillageReportDTO;
import com.zenhomes.consumptionreport.models.Reading;
import com.zenhomes.consumptionreport.models.Village;
import com.zenhomes.consumptionreport.repositories.ReadingRepository;
import com.zenhomes.consumptionreport.repositories.VillageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReportServiceImplTest {

    @Mock
    private ReadingRepository readingRepository;

    @Mock
    private VillageRepository villageRepository;

    @InjectMocks
    private ReportService service = new ReportServiceImpl();

    @BeforeEach
    void setMockOutput() {
        when(villageRepository.findAll()).thenReturn(Arrays.asList(
                new Village(10, "villaMine"),
                new Village(20, "villaYours")));

        when(readingRepository.findTopByVillageIdOrderByTimeDesc(10))
                .thenReturn(Optional.of(new Reading(1000,
                        new Date(1582917012000L),
                        1,
                        10,
                        "villaMine",
                        1100.123,
                        10.0)));

        when(readingRepository.findTopByVillageIdAndTimeAfterOrderByTimeAsc(eq(10), Mockito.any()))
                .thenReturn(Optional.of(new Reading(1,
                        new Date(1582837812000L),
                        1,
                        10,
                        "villaMine",
                        1000.123,
                        10.0)));

        when(readingRepository.findTopByVillageIdOrderByTimeDesc(20))
                .thenReturn(Optional.of(new Reading(1001,
                        new Date(1582917012000L),
                        2,
                        20,
                        "villaYours",
                        1200.123,
                        10.0)));

        when(readingRepository.findTopByVillageIdAndTimeAfterOrderByTimeAsc(eq(20), Mockito.any()))
                .thenReturn(Optional.of(new Reading(2,
                        new Date(1582837812000L),
                        2,
                        20,
                        "villaYours",
                        1010.123,
                        10.0)));
    }

    @Test
    public void whenQueriesReturnCorrectResults_getReport_ConsumptionReportGeneratesCorrectly() {
        var actual = service.getReport(24);
        var expected = Arrays.asList(new VillageReportDTO("villaMine", 100d),
                new VillageReportDTO("villaYours", 190d));

        assertEquals(actual, expected);

    }
}
