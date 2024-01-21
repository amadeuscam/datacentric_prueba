package com.amadeuscam.datacentric.Customers;

public interface Maper<A, B> {
    B mapTo(A a);

    A mapFrom(B b);
}
