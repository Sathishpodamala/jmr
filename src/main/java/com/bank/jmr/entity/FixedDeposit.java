package com.bank.jmr.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class FixedDeposit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double initialAmount;
    private float interestPercentage;

    private LocalDate createdAt;

    @OneToOne(mappedBy = "fixedDeposit")
    private Account account;

}
