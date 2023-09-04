package com.bank.jmr.service;

import com.bank.jmr.entity.Account;
import com.bank.jmr.entity.Customer;
import com.bank.jmr.entity.FixedDeposit;
import com.bank.jmr.exception.CustomerCustomException;
import com.bank.jmr.repository.AccountRepository;
import com.bank.jmr.repository.CustomerRepository;
import com.bank.jmr.repository.FixedDepositRepository;
import com.bank.jmr.util.InterestRateGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
public class FixedDepositService {

    @Value("${bank.customer.fixed-deposit.cap}")
    private int customerFDMaxPercentageOfIncome;

    @Value("${bank.total.fixed-deposit.cap}")
    private int totalFDMaxPercentageOfAllCustomersIncome;

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private FixedDepositRepository depositRepository;

    private InterestRateGenerator interestRateGenerator;

    @Autowired
    public FixedDepositService(AccountRepository accountRepository, CustomerRepository customerRepository,
                               FixedDepositRepository depositRepository, InterestRateGenerator interestRateGenerator) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.depositRepository = depositRepository;
        this.interestRateGenerator = interestRateGenerator;
    }

    public Account applyForFixedDeposit(long id, int amount)
    {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty())
        {
            throw new CustomerCustomException("Can't Find user with id: "+id);
        }
        Account account = customer.get().getAccount();
        double income=account.getIncome();

        if(!isFDWithInCapLimit(amount,income,customerFDMaxPercentageOfIncome))
        {
            throw new CustomerCustomException("You can't apply for fixed deposit it is more than "+customerFDMaxPercentageOfIncome+"% of income: "+income);
        }

        Double fdAmount=depositRepository.sumOfAllFDAmounts();
        double totalFDAmount = fdAmount!=null?fdAmount:0.0;
        Double totalIncome=accountRepository.sumOfAllCustomersIncome();
        double totalIncomeOfCustomers = totalIncome!=null?totalIncome:0.0;

        double finalFDAmount=totalFDAmount+amount;
        if(!isFDWithInCapLimit(finalFDAmount,totalIncomeOfCustomers,totalFDMaxPercentageOfAllCustomersIncome))
        {
            throw new CustomerCustomException("Bank reached Fixed Deposit Limit");
        }

        FixedDeposit fixedDeposit=FixedDeposit.builder()
                .initialAmount(amount)
                .interestPercentage(interestRateGenerator.generateInterestForFixedDeposit())
                .createdAt(LocalDate.now())
                .account(account)
                .build();
        account.setFixedDeposit(fixedDeposit);
        Account updatedAccount = accountRepository.save(account);
        return updatedAccount;
    }


    public Double getTotalFixedDepositAmount()
    {
        return depositRepository.sumOfAllFDAmounts();
    }
    private boolean isFDWithInCapLimit(double fixedDepositAmount,double income,int percentage)
    {
        double maxDeposit = (percentage / 100.0) * income;
        log.info("fd: {} income {}  percentage: {} maxDeposit: {}",fixedDepositAmount,income,percentage,maxDeposit);
        return fixedDepositAmount<maxDeposit;
    }
}
