package com.bank.jmr.service;

import com.bank.jmr.entity.Account;
import com.bank.jmr.entity.Customer;
import com.bank.jmr.entity.CustomerAcquisition;
import com.bank.jmr.exception.CustomerCustomException;
import com.bank.jmr.repository.AccountRepository;
import com.bank.jmr.repository.CustomerAcquisitionRepository;
import com.bank.jmr.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;

    private CustomerAcquisitionRepository acquisitionRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AccountRepository accountRepository,CustomerAcquisitionRepository acquisitionRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.acquisitionRepository=acquisitionRepository;
    }

    public Customer saveCustomer(Customer customer) {
        Customer customerExists = customerRepository.checkCustomerExists(customer.getUserName(), customer.getMobileNumber(), customer.getEmail(), customer.getAadharNumber());
        if (customerExists != null) {
            throw new CustomerCustomException("User Already Exists with same details");
        }



        customer.setActive(true);
        customer.setRole("USER");
        if (customer.getAccount() != null) {
            Account account = customer.getAccount();
            account.setCustomer(customer);
            account.setActive(true);
            account.setCreatedAt(LocalDateTime.now());
            account.setIncome(100.00);
        }

        log.info("\nService : \n" + customer);
        Customer savedCustomer = customerRepository.save(customer);

        if(!acquisitionRepository.existsByDate(LocalDate.now()))
        {
            CustomerAcquisition acquisition=new CustomerAcquisition();
            acquisition.setDate(LocalDate.now());
            acquisitionRepository.save(acquisition);
        }
        CustomerAcquisition todayAcquisition = acquisitionRepository.findByDate(LocalDate.now());
        todayAcquisition.getOnBoarded().add(savedCustomer);
        acquisitionRepository.save(todayAcquisition);

        return savedCustomer;
    }

    public Customer getCustomerById(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new CustomerCustomException("Customer not found with id: " + id);
        }
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer deleteCustomerById(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customer.get().setActive(false);
            customerRepository.save(customer.get());
            return customer.get();
        } else {
            throw new CustomerCustomException("Customer not found with id: " + id);
        }
    }
}
