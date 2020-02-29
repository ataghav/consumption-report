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
public class CallbackControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whenValidInputIsProvided_addReading_ReturnsStatusCode200() throws Exception {
        var postPayload = "{\n" +
                "\"counter_id\": \"1\",\n" +
                "\"amount\": 543432.876\n" +
                "}";

        var requestBuilder = MockMvcRequestBuilders
                .post("/counter_callback")
                .accept(MediaType.APPLICATION_JSON)
                .content(postPayload)
                .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void whenZeroAsIdIsProvided_addReading_RejectsWithStatusCode400() throws Exception {
        var postPayload = "{\n" +
                "\"counter_id\": \"0\",\n" +
                "\"amount\": 543432.876\n" +
                "}";

        var requestBuilder = MockMvcRequestBuilders
                .post("/counter_callback")
                .accept(MediaType.APPLICATION_JSON)
                .content(postPayload)
                .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void whenNonNumericIdIsProvided_addReading_RejectsWithStatusCode400() throws Exception {
        var postPayload = "{\n" +
                "\"counter_id\": \"A\",\n" +
                "\"amount\": 543432.876\n" +
                "}";

        var requestBuilder = MockMvcRequestBuilders
                .post("/counter_callback")
                .accept(MediaType.APPLICATION_JSON)
                .content(postPayload)
                .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void whenNegativeIdIsProvided_addReading_RejectsWithStatusCode400() throws Exception {
        var postPayload = "{\n" +
                "\"counter_id\": \"-1\",\n" +
                "\"amount\": 532.876\n" +
                "}";

        var requestBuilder = MockMvcRequestBuilders
                .post("/counter_callback")
                .accept(MediaType.APPLICATION_JSON)
                .content(postPayload)
                .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void whenFloatingPointIdIsProvided_addReading_RejectsWithStatusCode400() throws Exception {
        var postPayload = "{\n" +
                "\"counter_id\": \"2.2\",\n" +
                "\"amount\": 532.876\n" +
                "}";

        var requestBuilder = MockMvcRequestBuilders
                .post("/counter_callback")
                .accept(MediaType.APPLICATION_JSON)
                .content(postPayload)
                .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void whenNonNumericAmountIsProvided_addReading_RejectsWithStatusCode400() throws Exception {
        var postPayload = "{\n" +
                "\"counter_id\": \"1\",\n" +
                "\"amount\": 543W32.876\n" +
                "}";

        var requestBuilder = MockMvcRequestBuilders
                .post("/counter_callback")
                .accept(MediaType.APPLICATION_JSON)
                .content(postPayload)
                .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void whenNegativeAmountIsProvided_addReading_RejectsWithStatusCode400() throws Exception {
        var postPayload = "{\n" +
                "\"counter_id\": \"1\",\n" +
                "\"amount\": -345.873\n" +
                "}";

        var requestBuilder = MockMvcRequestBuilders
                .post("/counter_callback")
                .accept(MediaType.APPLICATION_JSON)
                .content(postPayload)
                .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
