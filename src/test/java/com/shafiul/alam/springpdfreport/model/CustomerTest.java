package com.shafiul.alam.springpdfreport.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    Customer customer;
    Long idVal = 34L;
    String firstName = "Shafiul";
    String lastName = "Alam";

    @Before
    public void setUp(){
        customer = new Customer();
        customer.setId(idVal);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
    }

    @Test
    public void getId() {
        assertEquals(idVal, customer.getId());
    }

    @Test
    public void getFirstName() {
        assertEquals(firstName, customer.getFirstName());
    }

    @Test
    public void getLastName() {
        assertEquals(lastName, customer.getLastName());
    }

}
