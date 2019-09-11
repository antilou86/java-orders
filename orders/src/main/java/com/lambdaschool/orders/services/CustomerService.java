package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "customerService")
public interface CustomerService {

    //defines base methods for search and crud functionality
    List<Customer> findAll();
    Customer findCustomerByName(String name);
    void delete(long id);
    Customer save(Customer customer);
    Customer update(Customer customer, long id);
}

