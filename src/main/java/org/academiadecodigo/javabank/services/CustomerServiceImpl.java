package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.persistence.model.AbstractModel;
import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.persistence.model.Recipient;
import org.academiadecodigo.javabank.persistence.model.account.Account;
import org.academiadecodigo.javabank.persistence.dao.CustomerDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * An {@link CustomerService} implementation
 */
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;

    /**
     * Sets the customer data access object
     *
     * @param customerDao the account DAO to set
     */
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * @see CustomerService#get(Integer)
     */
    @Override
    public Customer get(Integer id) {
        return customerDao.findById(id);
    }

    /**
     * @see CustomerService#getBalance(Integer)
     */
    @Override
    public double getBalance(Integer id) {

        Customer customer = Optional.ofNullable(customerDao.findById(id))
                .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

        return customer.getAccounts().stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }

    /**
     * @see CustomerService#listCustomerAccountIds(Integer)
     */
    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {

        Customer customer = Optional.ofNullable(customerDao.findById(id))
                .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

        return customer.getAccounts().stream()
                .map(AbstractModel::getId)
                .collect(Collectors.toSet());
    }

    /**
     * @see CustomerService#listRecipients(Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public List<Recipient> listRecipients(Integer id) {

        Customer customer = Optional.ofNullable(customerDao.findById(id))
                .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

        return new ArrayList<>(customer.getRecipients());
    }
   public List <Customer> getList (List <Integer> ids) {
        List <Customer> customerList = new ArrayList<>();
       for (Integer id: ids ) {
           customerList.add(get(id));
       }
       return customerList;
    }
}
