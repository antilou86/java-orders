package com.lambdaschool.orders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;


//Creating SQL table
@Entity
@Table (name = "orders")
public class Order
{
    //generates unique ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ordnum;

    private double ordamount;
    private double advanceamount;
    private String orddescription;

    //joins customer id.
    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false)
    @JsonIgnoreProperties("orders")
    private Customer customer;

    //default constructor
    public Order()
    { }

    // sets constructor fields
    public Order(double ordamount, double advanceamount, Customer customer, String orddescription )
    {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.orddescription = orddescription;
        this.customer = customer;
    }

    //auto-generated getters and setters
    public long getOrdnum()
    {
        return ordnum;
    }
    public void setOrdnum(long ordnum)
    {
        this.ordnum = ordnum;
    }
    public double getOrdamount()
    {
        return ordamount;
    }
    public void setOrdamount(double ordamount)
    {
        this.ordamount = ordamount;
    }
    public double getAdvanceamt()
    {
        return advanceamount;
    }
    public void setAdvanceamt(double advanceamount)
    {
        this.advanceamount = advanceamount;
    }
    public String getOrddescription()
    {
        return orddescription;
    }
    public void setOrddescription(String orddescription)
    {
        this.orddescription = orddescription;
    }
    public Customer getCustomer()
    {
        return customer;
    }
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
}