package com.gymmanagement.backend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false,unique = true)
    private Long customerId;

    @Column(name = "pack_id", nullable = false)
    private Long packId;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;


}