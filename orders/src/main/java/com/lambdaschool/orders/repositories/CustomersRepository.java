package com.lambdaschool.orders.repositories;

import com.lambdaschool.orders.models.Customer;
import org.springframework.data.repository.CrudRepository;

//interface for customer crud
public interface CustomersRepository extends CrudRepository<Customer, Long> {

    //this is the JPA thingy
    Customer findByCustname(String custname);
}

