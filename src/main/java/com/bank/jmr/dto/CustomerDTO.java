package com.bank.jmr.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor()
@NoArgsConstructor
@Builder
public class CustomerDTO {
    private long id;
    @NotBlank
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String userName;
    @NotBlank
    private String password;
    @Digits(integer = 10,fraction = 0,message = "10 Digit Mobile Number")
    private String mobileNumber;
    @Email
    private String email;
    @Digits(integer = 12,fraction = 0,message = "12 Digit Aadhar Number")
    private String aadharNumber;
    private boolean active;

    private AccountDTO account;

}
