package com.amadeuscam.datacentric.Customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CustomerRopository extends JpaRepository<Customer, String> {
}
