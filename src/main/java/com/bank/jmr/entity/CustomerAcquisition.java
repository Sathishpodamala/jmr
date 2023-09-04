package com.bank.jmr.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class CustomerAcquisition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;
    @OneToMany
    private List<Customer>onBoarded=new ArrayList<>();
    @OneToMany
    private List<Customer>offBoarded=new ArrayList<>();
}
