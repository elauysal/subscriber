package com.example.swadeveloperprototype.service;

import com.example.swadeveloperprototype.model.Subscriber;
import com.example.swadeveloperprototype.model.SubscriberWrapper;
import com.example.swadeveloperprototype.repository.SubscriberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class DataService {
    @Autowired
    private SubscriberRepository subscriberRepository;

    public void loadSubscribersFromJsonFileAndSaveToDatabase(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonData = new String(Files.readAllBytes(Paths.get(filePath)));
            SubscriberWrapper wrapper = mapper.readValue(jsonData, SubscriberWrapper.class);
            List<Subscriber> subscribers = wrapper.getSubscribers();
            subscriberRepository.saveAll(subscribers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @CachePut(value = "subscribersCache", key = "#subscriber.id")
    public Subscriber saveSubscriberToDatabaseAndCache(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }
    @Cacheable(value = "subscribersCache")
    public List<Subscriber> loadSubscribersFromDatabase() {
        return subscriberRepository.findAll();
    }
    @Cacheable(value = "subscribersCache", key = "#status")

    public List<Subscriber> loadActiveSubscribersFromDatabase() {
        return subscriberRepository.findByStatus("ACTIVE");
    }
    @CachePut(value = "subscribersCache", key = "#subscriber.id")

    public void saveSubscriber(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
    }
    @CacheEvict(value = "subscribersCache", key = "#id")

    public void deleteSubscriberById(Long id) {
        subscriberRepository.deleteById(id);
    }
}
