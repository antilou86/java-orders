package com.lambdaschool.orders.controllers;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

//annotations for rest controller and mapping.
@RestController
@RequestMapping("/data/customers")
@Component
public class CustomerController {
    //grabbin those beans by ANY MEANS NECESSARY.
    @Autowired
    private CustomerService customerService;

    //grab all customers and the orders they've made
    //http://localhost:2019/data/customers/orders

    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers() {
        List<Customer> myCustomers = customerService.findAll();
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

    //find customers by name
    //http://localhost:2019/data/customers/{name}

    @GetMapping(value = "/{name}", produces = {"application/json"})
    public ResponseEntity<?> getCustomerByName(
            @PathVariable String name) {
        Customer c = customerService.findCustomerByName(name);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    //add a customer
    //http://localhost:2019/data/customers/new

    @PostMapping(value = "/new")
    public ResponseEntity<?> saveCustomer(
            @Valid @RequestBody Customer newCustomer
    ){
        //adds the customer if the reponse is valid and sets up a response for customer ID
        newCustomer = customerService.save(newCustomer);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{custcode}").buildAndExpand(newCustomer.getCustcode()).toUri();
        responseHeaders.setLocation(newCustomerURI);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //update customer without changing order
    //http://localhost:2019/data/customers/{code}
    @PutMapping(value = "/{custcode}")
    public ResponseEntity<?> updateCustomer(
            @RequestBody Customer updateCustomer,
                @PathVariable long custcode)
    {
        customerService.update(updateCustomer, custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
