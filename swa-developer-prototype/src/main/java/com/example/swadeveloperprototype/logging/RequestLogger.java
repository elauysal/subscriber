package com.example.swadeveloperprototype.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class RequestLogger {

    private static final Logger logger = LoggerFactory.getLogger(RequestLogger.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void logRequest(String requestType, String requestDetails) {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.info("{} {} {}", timestamp, requestType, requestDetails);
    }
}
