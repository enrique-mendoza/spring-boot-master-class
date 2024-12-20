package com.example.demo.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    private CustomerService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CustomerService(customerRepository);
    }

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    void getCustomers() {
        // given:
        Customer jamila = new Customer(
                1L,
                "Jamila",
                "pwd",
                "test@mail"
        );

        Customer al = new Customer(
                2L,
                "Ali",
                "pwd2",
                "test2@mail"
        );

        customerRepository.saveAll(Arrays.asList(jamila, al));

        // when:
        List<Customer> customers = underTest.getCustomers();

        // then:
        assertEquals(2, customers.size());
    }

    @Test
    void getCustomer() {
        // given:
        Customer jamila = new Customer(
                1L,
                "Jamila",
                "pwd",
                "test@mail"
        );

        customerRepository.save(jamila);

        // when:
        Customer actual = underTest.getCustomer(1L);

        // then:
        assertEquals(1L, actual.getId());
        assertEquals("Jamila", actual.getName());
        assertEquals("pwd", actual.getPassword());
        assertEquals("test@mail", actual.getEmail());
    }
}