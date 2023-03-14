package org.academiadecodigo.javabank.Services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.view.MainView;

import java.util.*;

public class CustomerServ implements CustomerService {


    Authentication authentication;
    List<Customer> customerList;
    Set<Integer> idList;
    private HashMap<Integer, Customer> customers;
    Customer customer;

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    MainView mainView;

    public CustomerServ() {
        this.customers = new HashMap<>();
    }

    @Override
    public Customer get(Integer id) {
        return customers.get(id);
    }

    @Override
    public List<Customer> list() {
        customerList = new ArrayList<>();
        customerList.add(customer);
        return customerList;
    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {
        idList = new HashSet<>();
        idList.add(id);
        return idList;
    }

    @Override
    public double getBalance(int customerId) {
        return 0;
    }

    @Override
    public void add(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }


}
