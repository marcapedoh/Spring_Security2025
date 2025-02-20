package com.gymmanagement.backend.repository;

import com.gymmanagement.backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Additional query methods can be defined here if needed
    Optional<Customer> findByLastName(String lastName);
}