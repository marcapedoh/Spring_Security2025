package com.gymmanagement.backend.controller;

import com.gymmanagement.backend.model.Subscription;
import com.gymmanagement.backend.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

   /* @PostMapping
    public ResponseEntity<Subscription> subscribeCustomer(@RequestBody Subscription subscription) {
        Subscription createdSubscription = subscriptionService.subscribeCustomer(subscription);
        return ResponseEntity.ok(createdSubscription);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Subscription> getCustomerSubscription(@PathVariable Long customerId) {
        Subscription subscription = subscriptionService.getCustomerSubscription(customerId);
        return ResponseEntity.ok(subscription);
    }

    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<Void> cancelSubscription(@PathVariable Long subscriptionId) {
        subscriptionService.cancelSubscription(subscriptionId);
        return ResponseEntity.noContent().build();
    }
*/
    @GetMapping
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }
}