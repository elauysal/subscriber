package com.example.swadeveloperprototype.controller;

import com.example.swadeveloperprototype.cache.SubscriberCache;
import com.example.swadeveloperprototype.model.Subscriber;
import com.example.swadeveloperprototype.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//RestAPİ
@RestController
@RequestMapping("/api/subscribers")
public class SubscriberController {
    @Autowired
    private SubscriberCache subscriberCache;

    @Autowired
    private DataService dataService;

    @GetMapping("/getAllSubscribers")
    public List<Subscriber> getAllSubscribers() {
        return dataService.loadSubscribersFromDatabase();
    }

    @GetMapping("/getActiveSubscribers")
    public List<Subscriber> getActiveSubscribers() {
        return dataService.loadActiveSubscribersFromDatabase();
    }

    @GetMapping("/getSubscriberById/{id}")
    public ResponseEntity<Subscriber> getSubscriberById(@PathVariable Long id) {
        return subscriberCache.getSubscribers().stream()
                .filter(subscriber -> subscriber.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/subscriber")
    public ResponseEntity<Subscriber> createSubscriber(@RequestBody Subscriber subscriber) {
        subscriberCache.getSubscribers().add(subscriber);
        dataService.saveSubscriber(subscriber);  // Veritabanına kaydet
        return ResponseEntity.ok(subscriber);
    }

    @PutMapping("/subscriber/{id}")
    public ResponseEntity<Subscriber> updateSubscriber(@PathVariable Long id, @RequestBody Subscriber updatedSubscriber) {
        Optional<Subscriber> subscriberOptional = subscriberCache.getSubscribers().stream()
                .filter(subscriber -> subscriber.getId().equals(id))
                .findFirst();
        if (subscriberOptional.isPresent()) {
            Subscriber subscriber = subscriberOptional.get();
            subscriber.setName(updatedSubscriber.getName());
            subscriber.setMsisdn(updatedSubscriber.getMsisdn());
            subscriber.setStatus(updatedSubscriber.getStatus());
            dataService.saveSubscriber(subscriber);  // Veritabanında güncelle
            return ResponseEntity.ok(subscriber);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/subscriber/{id}")
    public ResponseEntity<Void> deleteSubscriber(@PathVariable Long id) {
        boolean isRemoved = subscriberCache.getSubscribers().removeIf(subscriber -> subscriber.getId().equals(id));
        if (isRemoved) {
            dataService.deleteSubscriberById(id);  // Veritabanından sil
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
