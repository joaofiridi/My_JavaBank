package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaCustomerDao;

import java.util.Optional;

/**
 * An {@link AuthService} implementation
 */
public class AuthServiceImpl implements AuthService {

    private CustomerService customerService;
    private Integer accessingCustomerId;



    private JpaCustomerDao jpaCustomerDao;

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
    public void setJpaCustomerDao(JpaCustomerDao jpaCustomerDao) {
        this.jpaCustomerDao = jpaCustomerDao;
    }
    /**
     * @see AuthService#authenticate(Integer)
     */
    @Override
    public boolean authenticate(Integer id) {

        Optional<Customer> customer = Optional.ofNullable(jpaCustomerDao.findById(id));

        customer.ifPresent(customer1 -> accessingCustomerId = customer1.getId());

        return customer.isPresent();

    }

    /**
     * @see AuthService#getAccessingCustomer()
     */
    @Override
    public Customer getAccessingCustomer() {
        return jpaCustomerDao.findById(accessingCustomerId);
    }
}
