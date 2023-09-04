package com.bank.jmr.controller;

import com.bank.jmr.dto.AccountDTO;
import com.bank.jmr.dto.CustomerDTO;
import com.bank.jmr.entity.Account;
import com.bank.jmr.entity.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    @GetMapping("/customers")
    public List<CustomerDTO>getCustomers()
    {
        List<CustomerDTO>customers=new ArrayList<>();
        String allNames="Anabelle Daniels,Maxwell Isom,Tammy Gale,Cordell Harman,Jaylynn Freeland,Wyatt Callaway," +
                "Duncan Chaffin,Frederick Dubois,Meagan Peek,Raelyn Jordan,Daniella Mock,Lester Faust,Juancarlos Samples," +
                "Cailey Gandy,Abrianna Colson,Colby Alley,Charlotte Schmitt,Kayla Damron,Tristian Shin,Kinsley Tirado,Lexy Jeffrey," +
                "Kendall Barnhart,Owen Arreola,Mustafa Escalante,Rosemarie Fultz";
        String[] splitedNames = allNames.split(",");
        List<String>names= List.of(splitedNames);

        for(String name:names)
        {
            String[] splitedName = name.split(" ");
            CustomerDTO customerDTO=new CustomerDTO();
            customerDTO.setFirstName(splitedName[0]);
            customerDTO.setLastName(splitedName[1]);
            customerDTO.setUserName(splitedName[1]+getRandomNumber());
            customerDTO.setPassword(splitedName[0].toUpperCase());
            customerDTO.setMobileNumber(getRandomNumber()+""+getRandomNumber());
            customerDTO.setEmail(splitedName[0]+"@gmail.com");
            customerDTO.setAadharNumber(getRandomNumber()+""+getRandomNumber()+""+getRandomNumber());

            customers.add(customerDTO);
        }

        return customers;
    }

    private long getRandomNumber()
    {
        int min=1234;
        int max=9999;
        Random random=new Random();
        return random.nextInt(max-min+1)+min;
    }
}
