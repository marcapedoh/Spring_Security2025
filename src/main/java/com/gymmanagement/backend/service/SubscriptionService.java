package com.gymmanagement.backend.service;


import com.gymmanagement.backend.model.Subscription;
import com.gymmanagement.backend.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription createSubscription(Subscription subscription) {
        subscription.setStartDate(LocalDate.now());
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Optional<Subscription> getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

    public Optional<Subscription> getSubscriptionByCustomerId(Long customerId){
        return this.subscriptionRepository.findByCustomerId(customerId);
    }
    public Subscription updateSubscription(Long id, Subscription subscriptionDetails) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found with id " + id));
        subscription.setCustomerId(subscriptionDetails.getCustomerId());
        subscription.setPackId(subscriptionDetails.getPackId());
        subscription.setStartDate(subscriptionDetails.getStartDate());
        return subscriptionRepository.save(subscription);
    }

    public void deleteSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found with id " + id));
        subscriptionRepository.delete(subscription);
    }
}