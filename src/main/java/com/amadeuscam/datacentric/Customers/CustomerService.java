package com.amadeuscam.datacentric.Customers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Optional<Customer> getCustomer(String dni);


    Boolean isCustomerExistsByDni(String dni);

    Page<Customer> findAll(Pageable pageable);

    void delete(String dni);
}
