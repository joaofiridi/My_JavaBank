package org.academiadecodigo.javabank.persistence.dao;

import org.academiadecodigo.javabank.model.Customer;

import java.util.List;

public interface CustomerDao {

    List<Customer> findAll();

    Customer findById(Integer id);

    Customer saveOrUpdate(Customer customer);

    Void delete (Integer id);

    Customer findByUsername(String firstName);

    Customer findByEmail (String email);
}
