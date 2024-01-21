package com.amadeuscam.datacentric.Customers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private String dni;
    private String name;
    private String firstLastName;
    private String secondLastName;
    private String address;
}
