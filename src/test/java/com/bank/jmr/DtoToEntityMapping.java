package com.bank.jmr;

import com.bank.jmr.dto.AccountDTO;
import com.bank.jmr.dto.CustomerDTO;
import com.bank.jmr.entity.Account;
import com.bank.jmr.entity.Customer;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DtoToEntityMapping {

    @Test
    public void dtoToEntityTest()
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
        customerdto.setActive(true);
        customerdto.setMobileNumber("9989863241");
        customerdto.setAadharNumber("377067210494");
        customerdto.setAccount(account);

        ModelMapper mapper=new ModelMapper();
        Customer customer=mapper.map(customerdto, Customer.class);
        System.out.println(customerdto);
        System.out.println(customer);

        assertEquals(customerdto.isActive(),customer.isActive());
    }

    @Test
    public void entityToDtoTest()
    {
        Account account = new Account();
        account.setCreatedAt(LocalDateTime.now());
        account.setActive(true);

        Customer customer=new Customer();
        customer.setId(0l);
        customer.setFirstName("Sathish");
        customer.setLastName("Reddy");
        customer.setUserName("Sathish3");
        customer.setEmail("sathish@gmail.com");
        customer.setPassword("MsDhoni");
        customer.setActive(true);
        customer.setMobileNumber("9989863241");
        customer.setAadharNumber("377067210494");
        customer.setAccount(account);

        ModelMapper mapper=new ModelMapper();
        CustomerDTO customerDTO=mapper.map(customer, CustomerDTO.class);
        System.out.println(customer);
        System.out.println(customerDTO);

        assertEquals(customer.isActive(),customer.isActive());
    }

    @Test
    public void calculatePercentage()
    {
        double percentage=50;
        double income=20000;
        double maxDeposit = percentage / 100.0 * income;
        System.out.println(percentage / 100.0);
        System.out.println(((percentage / 100.0) * income));
        assertEquals(10000,maxDeposit);
    }
}
