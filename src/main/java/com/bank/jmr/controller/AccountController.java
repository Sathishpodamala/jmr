package com.bank.jmr.controller;

import com.bank.jmr.dto.AccountDTO;
import com.bank.jmr.entity.Account;
import com.bank.jmr.repository.AccountRepository;
import com.bank.jmr.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@RestController
@RequestMapping("/api/account")
public class AccountController {

    AccountService accountService;

    ModelMapper modelMapper;

    @Autowired
    public AccountController(AccountService accountService, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return accounts.stream().map(cus -> modelMapper.map(cus, AccountDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/totalIncome")
    public double getTotalFixedDepositAmount()
    {
        Double totalIncome= accountService.getTotalIncomeOfAllCustomers();
        return totalIncome!=null?totalIncome:0.0;
    }
}
