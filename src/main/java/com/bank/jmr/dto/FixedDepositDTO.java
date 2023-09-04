package com.bank.jmr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FixedDepositDTO {
    private double initialAmount;
    private float interestPercentage;
    private LocalDate createdAt;
}
