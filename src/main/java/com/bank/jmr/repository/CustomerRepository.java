package com.bank.jmr.repository;

import com.bank.jmr.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT c FROM Customer c WHERE c.userName=:userName OR c.mobileNumber=:mobileNumber OR c.email=:email OR c.aadharNumber=:aadharNumber")
    Customer checkCustomerExists(String userName,String mobileNumber,String email,String aadharNumber);
}
