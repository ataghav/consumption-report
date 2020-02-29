package com.zenhomes.consumptionreport.controllers;

import com.zenhomes.consumptionreport.ConsumptionReportApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = ConsumptionReportApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ReportControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenCorrectDurationIsProvided_getReport_ReturnsStatusCode200() throws Exception {
        var requestBuilder = MockMvcRequestBuilders
                .get("/consumption_report?duration=24h")
                .accept(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void whenUnexpectedDurationIsProvided_getReport_RejectsWithStatusCode400() throws Exception {
        var requestBuilder = MockMvcRequestBuilders
                .get("/consumption_report?duration=24")
                .accept(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
