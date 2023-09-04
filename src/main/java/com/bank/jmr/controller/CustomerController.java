package com.bank.jmr.controller;

import com.bank.jmr.dto.AccountDTO;
import com.bank.jmr.dto.CustomerDTO;
import com.bank.jmr.entity.Account;
import com.bank.jmr.entity.Customer;
import com.bank.jmr.repository.AccountRepository;
import com.bank.jmr.repository.CustomerRepository;
import com.bank.jmr.service.CustomerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private CustomerService customerService;
    private ModelMapper modelMapper;

    @Autowired
    public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save")
    public CustomerDTO saveCustomer( @Valid @RequestBody CustomerDTO customerDTO) {

        Customer customer = modelMapper.map(customerDTO, Customer.class);

        Customer savedCustomer = customerService.saveCustomer(customer);

        CustomerDTO responseCustomerDto=modelMapper.map(savedCustomer, CustomerDTO.class);
        return responseCustomerDto;
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable long id) {
        Customer customer = customerService.getCustomerById(id);
        return modelMapper.map(customer, CustomerDTO.class);
    }

    @DeleteMapping("/{id}")
    public Customer deleteCustomerById(@PathVariable long id) {
        customerService.deleteCustomerById(id);
        return null;
    }

    @GetMapping("/all")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers()
                .stream()
                .map(temp -> modelMapper.map(temp, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/bulk")
    public List<CustomerDTO> bulkRegistration(@RequestBody List<CustomerDTO>bulkCustomersDtos)
    {
        List<CustomerDTO>savedCustomers=new ArrayList<>();
       for(CustomerDTO customerDTO:bulkCustomersDtos)
       {
           savedCustomers.add(saveCustomer(customerDTO));
       }
        return savedCustomers;
    }
}
