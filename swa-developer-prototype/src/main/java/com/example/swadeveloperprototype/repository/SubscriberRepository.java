package com.example.swadeveloperprototype.repository;

import com.example.swadeveloperprototype.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    List<Subscriber> findByStatus(String status);
    Optional<Subscriber> findById(Long id);
}
