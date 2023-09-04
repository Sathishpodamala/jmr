package com.bank.jmr.repository;

import com.bank.jmr.entity.CustomerAcquisition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CustomerAcquisitionRepository extends JpaRepository<CustomerAcquisition,Long> {

    CustomerAcquisition findByDate(LocalDate date);

    boolean existsByDate(LocalDate date);
}
