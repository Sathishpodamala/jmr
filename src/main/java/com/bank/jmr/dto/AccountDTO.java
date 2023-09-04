package com.bank.jmr.dto;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountDTO {
    private Long accountNumber;
    private LocalDateTime createdAt;
    private boolean active;
    private double income;
}
