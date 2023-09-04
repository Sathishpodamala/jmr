package com.bank.jmr.repository;

import com.bank.jmr.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account,Long> {

    @Query("SELECT SUM(a.income) FROM Account a")
    Double sumOfAllCustomersIncome();
}
