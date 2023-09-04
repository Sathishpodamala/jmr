package com.bank.jmr.controller;

import com.bank.jmr.dto.AccountDTO;
import com.bank.jmr.entity.Account;
import com.bank.jmr.service.FixedDepositService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fixedDeposit")
public class FixedDepositController {

    private FixedDepositService depositService;
    private ModelMapper modelMapper;

    @Autowired
    public FixedDepositController(FixedDepositService depositService, ModelMapper modelMapper) {
        this.depositService = depositService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/apply")
    public AccountDTO applyForFixedDeposit(@RequestParam long id, @RequestParam int depositAmount)
    {
        Account account = depositService.applyForFixedDeposit(id, depositAmount);

        return modelMapper.map(account, AccountDTO.class);
    }

    @GetMapping("/totalAmount")
    public double getTotalFixedDepositAmount()
    {
        Double totalDeposit= depositService.getTotalFixedDepositAmount();
        return totalDeposit!=null?totalDeposit:0.0;
    }
}
