package com.amadeuscam.datacentric.Customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRopository customerRopository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRopository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRopository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomer(String dni) {
        return customerRopository.findById(dni);
    }

    @Override
    public Boolean isCustomerExistsByDni(String dni) {
        return customerRopository.existsById(dni);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRopository.findAll(pageable);
    }

    @Override
    public void delete(String dni) {
        customerRopository.deleteById(dni);
    }
}
