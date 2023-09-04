package com.bank.jmr.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String lastName;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @NotBlank
    private String mobileNumber;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String aadharNumber;
    private boolean active;

    private AccountDTO account;

}
