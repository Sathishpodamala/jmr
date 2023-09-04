package com.bank.jmr.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class IncomeGenerator {

    @Value("${bank.customer.income.min}")
    private double incomeMin;
    @Value("${bank.customer.income.max}")
    private double incomeMax;
    private Random random;

    public IncomeGenerator() {
        random=new Random();
    }

    public double getRandomIncome() {
        System.out.println(incomeMin);
        System.out.println(incomeMax);
        return random.nextDouble(incomeMax-incomeMin+1)+incomeMin;
    }
}
