package com.bank.jmr.service;

import com.bank.jmr.dto.AccountDTO;
import com.bank.jmr.entity.Account;
import com.bank.jmr.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    public Double getTotalIncomeOfAllCustomers()
    {
        return accountRepository.sumOfAllCustomersIncome();
    }
}
