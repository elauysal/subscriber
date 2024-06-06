package com.example.swadeveloperprototype.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${app.data-source-type}")
    private String dataSourceType;

    @Value("${app.data-file-path}")
    private String dataFilePath;

    @Value("${app.cron-expression}")
    private String cronExpression;

    // Getter for data source type
    public String getDataSourceType() {
        return dataSourceType;
    }

    // Getter for data file path
    public String getDataFilePath() {
        return dataFilePath;
    }

    // Getter for cron expression
    public String getCronExpression() {
        return cronExpression;
    }
}
