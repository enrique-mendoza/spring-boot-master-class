package com.example.demo.customer;

import java.util.Arrays;
import java.util.List;

public class CustomerFakeRepository implements CustomerRepo {

    @Override
    public List<Customer> getCustomers() {
        return Arrays.asList(
                new Customer(1L, "James Bond", "pass1", "mail1@mail.com"),
                new Customer(2L, "Jamila Ahmed", "pass2", "mail2@mail.com")
        );
    }
}
