package com.zenhomes.consumptionreport.controllers;

import com.zenhomes.consumptionreport.ConsumptionReportApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = ConsumptionReportApplication.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ReadingReportIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void gettingBackProvidedReading() throws Exception {
        var postPayload = "{\n" +
                "\"counter_id\": \"1\",\n" +
                "\"amount\": 543432.876\n" +
                "}";

        var postRequestBuilder = MockMvcRequestBuilders
                .post("/counter_callback")
                .accept(MediaType.APPLICATION_JSON)
                .content(postPayload)
                .contentType(MediaType.APPLICATION_JSON);

        var postResult = mockMvc.perform(postRequestBuilder).andReturn();
        var postResponse = postResult.getResponse();
        assertThat(postResponse.getStatus()).isEqualTo(HttpStatus.OK.value());

        var readingId = Integer.parseInt(postResponse.getContentAsString());

        var getRequestBuilder = MockMvcRequestBuilders
                .get("/readings/" + readingId)
                .accept(MediaType.APPLICATION_JSON);

        var getResult = mockMvc.perform(getRequestBuilder).andReturn();
        var getResponse = getResult.getResponse();
        assertThat(getResponse.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expected = "{id:" + readingId + ", amount:543432.876}";
        JSONAssert.assertEquals(expected, getResponse.getContentAsString(), false);
    }
}
