package com.amadeuscam.datacentric.Customers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements Maper<Customer, CustomerDto> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomerDto mapTo(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public Customer mapFrom(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }
}
