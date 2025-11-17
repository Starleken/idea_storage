package com.protobin.historyservicestarter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "history-service-starter")
public class HistoryServiceStarterMapProperties {

    private String kafkaUrl;

    public String getKafkaUrl() {
        return kafkaUrl;
    }

    public void setKafkaUrl(String kafkaUrl) {
        this.kafkaUrl = kafkaUrl;
    }
}
