package org.academiadecodigo.javabank.Services;

import org.academiadecodigo.javabank.model.Customer;

public interface AuthService {

    boolean authenticate (Integer id);
    Customer getAccessingCustomer();
}
