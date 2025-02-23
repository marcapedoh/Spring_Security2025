package com.gymmanagement.backend.controller;

import com.gymmanagement.backend.model.Subscription;
import com.gymmanagement.backend.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.gymmanagement.backend.constant.Utils.APP_ROOT;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(APP_ROOT+"subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/create")
    public ResponseEntity<Subscription> subscribeCustomer(@RequestBody Subscription subscription) {
        Subscription createdSubscription = subscriptionService.createSubscription(subscription);
        return ResponseEntity.ok(createdSubscription);
    }

        @GetMapping("/customerSubscriptionByCustomerId/{customerId}")
        public ResponseEntity<Subscription> getCustomerSubscription(@PathVariable Long customerId) {
            Optional<Subscription> subscription = subscriptionService.getSubscriptionByCustomerId(customerId);
            return ResponseEntity.ok(subscription.get());
        }
          @DeleteMapping("/delete/{subscriptionId}")
          public ResponseEntity<Void> cancelSubscription(@PathVariable Long subscriptionId) {
              subscriptionService.deleteSubscription(subscriptionId);
              return ResponseEntity.noContent().build();
          }

    @GetMapping("/all")
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }
}