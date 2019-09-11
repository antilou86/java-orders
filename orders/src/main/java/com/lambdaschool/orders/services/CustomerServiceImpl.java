package com.lambdaschool.orders.services;

import com.lambdaschool.orders.models.Customer;
import com.lambdaschool.orders.models.Order;
import com.lambdaschool.orders.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomersRepository custrepos;

    //get all customers handler
    @Override
    public List<Customer> findAll()
    {
        List<Customer> list = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    //get single customer handler
    @Override
    public Customer findCustomerByName(String name)
    {
        Customer currentCustomer = custrepos.findByCustname(name);
        if(currentCustomer != null)
        {
            return currentCustomer;
        }else
        {
            throw new EntityNotFoundException(name);
        }
    }

    //delete handler
    @Override
    @Transactional
    public void delete(long id)
    {
        if(custrepos.findById(id).isPresent())
        {
            custrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    //post request handler
    @Override
    @Transactional
    public Customer save(Customer customer)
    {
        Customer newCustomer = new Customer();
        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        for(Order o : customer.getOrders())
        {
            newCustomer.getOrders().add(new Order(o.getOrdamount(), o.getAdvanceamt(), newCustomer, o.getOrddescription()));
        }
        newCustomer.setAgent(customer.getAgent());

        return custrepos.save(newCustomer);
    }

    //put request handler
    @Override
    @Transactional
    public Customer update(Customer customer, long id)
    {
        Customer currentCustomer = custrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        //if the out request comes with a null field this will skip it entirely. ;)
        if (customer.getCustname() != null) {
            currentCustomer.setCustname(customer.getCustname());
        }
        if (customer.getCustcity() != null) {
            currentCustomer.setCustcity(customer.getCustcity());
        }
        if (customer.getWorkingarea() != null) {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }
        if (customer.getCustcountry() != null) {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }
        if (customer.getGrade() != null) {
            currentCustomer.setGrade(customer.getGrade());
        }
        if (customer.getOpeningamt() > 0) {
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }
        if (customer.getReceiveamt() > 0) {
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }
        if (customer.getPaymentamt() > 0) {
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }
        if (customer.getPhone() != null) {
            currentCustomer.setPhone(customer.getPhone());
        }
        return custrepos.save(currentCustomer);
    }
}
