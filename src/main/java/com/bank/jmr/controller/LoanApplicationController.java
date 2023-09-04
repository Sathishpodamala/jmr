package com.bank.jmr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanApplicationController {

    @GetMapping("/apply")
    public void applyForLoan(@RequestParam long id, @RequestParam int amount,@RequestParam String loanType)
    {

    }
}
