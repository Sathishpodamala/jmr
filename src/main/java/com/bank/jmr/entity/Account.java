package com.bank.jmr.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = "customer")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "custom_accountno_generator")
    @SequenceGenerator(name = "custom_accountno_generator",sequenceName = "accNo_seq",initialValue = 111111,allocationSize = 1)
    private Long accountNumber;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private double income;

    private boolean active;

    @OneToOne(mappedBy = "account",fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
