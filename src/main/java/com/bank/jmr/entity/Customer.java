package com.bank.jmr.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true,updatable = false,nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(unique = true,nullable = false)
    private String mobileNumber;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(unique = true,nullable = false)
    private String aadharNumber;

    private boolean active;
    private String role;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "account_Number")
    private Account account;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private CustomerAcquisition acquisition;

}
