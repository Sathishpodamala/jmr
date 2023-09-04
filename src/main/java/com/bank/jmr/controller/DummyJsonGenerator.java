package com.bank.jmr.controller;

import com.bank.jmr.dto.AccountDTO;
import com.bank.jmr.dto.CustomerDTO;
import com.bank.jmr.entity.Account;
import com.bank.jmr.entity.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/dummy")
public class DummyJsonGenerator {

    @GetMapping("/customer")
    public CustomerDTO getCustomer()
    {
        AccountDTO account = new AccountDTO();
        account.setCreatedAt(LocalDateTime.now());
        account.setActive(true);

        CustomerDTO customerdto=new CustomerDTO();
        customerdto.setFirstName("Sathish");
        customerdto.setLastName("Reddy");
        customerdto.setUserName("Sathish3");
        customerdto.setEmail("sathish@gmail.com");
        customerdto.setPassword("MsDhoni");
        customerdto.setMobileNumber("9989863241");
        customerdto.setAadharNumber("377067210494");
        customerdto.setAccount(account);
        return customerdto;
    }
}
