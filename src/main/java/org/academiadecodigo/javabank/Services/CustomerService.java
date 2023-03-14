package org.academiadecodigo.javabank.Services;

import org.academiadecodigo.javabank.model.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    Customer get (Integer id);

    List<Customer> list();

    Set<Integer> listCustomerAccountIds (Integer id);

    double getBalance (int customerId);

    void add(Customer customer);


}
