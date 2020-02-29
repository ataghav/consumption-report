package com.zenhomes.consumptionreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Hashtable;

@SpringBootApplication
public class ConsumptionReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumptionReportApplication.class, args);
    }

    @Bean
    public WebClient.Builder getWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public Hashtable<String, Integer> getReportDuration() {
        Hashtable<String, Integer> ReportDuration = new Hashtable<>();

        ReportDuration.put("24h", 24);
        ReportDuration.put("1w", 168);
        ReportDuration.put("30d", 720);

        return ReportDuration;
    }
}

//validation
//try catch
