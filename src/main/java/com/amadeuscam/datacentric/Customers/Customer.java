package com.amadeuscam.datacentric.Customers;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    private String dni;
    private String name;
    private String firstLastName;
    private String secondLastName;
    private String address;
    private Boolean active = true;
}
