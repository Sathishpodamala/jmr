package com.bank.jmr.repository;

import com.bank.jmr.entity.FixedDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FixedDepositRepository extends JpaRepository<FixedDeposit,Long> {

    @Query("SELECT SUM(f.initialAmount) FROM FixedDeposit f")
    Double sumOfAllFDAmounts();
}
