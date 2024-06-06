package com.example.swadeveloperprototype.job;

import com.example.swadeveloperprototype.cache.SubscriberCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheUpdateJob {
    @Autowired
    private SubscriberCache subscriberCache;

    @Scheduled(cron = "0 0 * * * *")
    public void updateCacheFromDatabase() {
        subscriberCache.refreshCache();
    }
}
