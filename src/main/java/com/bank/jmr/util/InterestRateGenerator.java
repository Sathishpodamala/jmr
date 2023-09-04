package com.bank.jmr.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class InterestRateGenerator {

    //bank.fixed-deposit.interest.min=5
    @Value("${bank.fixed-deposit.interest.min}")
    private float interestForFDMin;

    //bank.fixed-deposit.interest.max=20
    @Value("${bank.fixed-deposit.interest.max}")
    private float interestForFDMax;


    private Random random;

    public InterestRateGenerator() {
        random=new Random();
    }

    public float generateInterestForFixedDeposit()
    {
        return random.nextFloat(interestForFDMax-interestForFDMin+1)+interestForFDMin;
    }
}
