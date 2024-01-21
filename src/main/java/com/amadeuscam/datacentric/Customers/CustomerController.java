package com.amadeuscam.datacentric.Customers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final Maper<Customer, CustomerDto> customerMaper;

    @PostMapping(value = "customers")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto) {
        final Customer customer = customerMaper.mapFrom(customerDto);
        if (customerService.isCustomerExistsByDni(customer.getDni())) {
//            return new ResponseEntity<>("Registro existente por DNI", HttpStatus.BAD_REQUEST);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Registro existente por DNI");
        }
        final Customer savedCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(customerMaper.mapTo(savedCustomer), HttpStatus.CREATED);
    }

    @PutMapping(value = "customers")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDto customerDto) {
        final Customer customer = customerMaper.mapFrom(customerDto);
        if (!customerService.isCustomerExistsByDni(customer.getDni())) {
            return new ResponseEntity<>("Dni no facilitado", HttpStatus.BAD_REQUEST);
        }
        final Customer savedCustomer = customerService.updateCustomer(customer);
        return new ResponseEntity<>(customerMaper.mapTo(savedCustomer), HttpStatus.OK);
    }

    @GetMapping(value = "customers/{dni}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("dni") String dni) {
        final Optional<Customer> customer = customerService.getCustomer(dni);
        return customer.map(cust -> {
            final CustomerDto customerDto = customerMaper.mapTo(cust);
            return new ResponseEntity<>(customerDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "customers")
    public Page<CustomerDto> getCustomers(Pageable pageable) {
        final Page<Customer> customers = customerService.findAll(pageable);
        return customers.map(customerMaper::mapTo);
    }

    @DeleteMapping(value = "customers/{dni}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("dni") String dni) {
        if (!customerService.isCustomerExistsByDni(dni)) {
            return new ResponseEntity<>("Registro no existente por DNI", HttpStatus.BAD_REQUEST);
        }
        customerService.delete(dni);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
