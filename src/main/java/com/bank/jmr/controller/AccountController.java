package com.bank.jmr.controller;

import com.bank.jmr.dto.AccountDTO;
import com.bank.jmr.entity.Account;
import com.bank.jmr.repository.AccountRepository;
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

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        for (Account account:accounts)
        {
            log.info(account+" \t -> "+account.getCustomer());
        }
        return accounts.stream().map(cus -> modelMapper.map(cus, AccountDTO.class)).collect(Collectors.toList());
    }

}
