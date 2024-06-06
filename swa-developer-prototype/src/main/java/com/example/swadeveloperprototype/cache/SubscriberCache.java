package com.example.swadeveloperprototype.cache;

import com.example.swadeveloperprototype.model.Subscriber;
import com.example.swadeveloperprototype.service.DataService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SubscriberCache {

    @Autowired
    private DataService dataService;

    private List<Subscriber> subscribers = new CopyOnWriteArrayList<>();

    @PostConstruct
    public void init() {
        refreshCache();
    }

    public void refreshCache() {
        List<Subscriber> loadedSubscribers = dataService.loadSubscribersFromDatabase();
        subscribers.clear();
        subscribers.addAll(loadedSubscribers);
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }
}
