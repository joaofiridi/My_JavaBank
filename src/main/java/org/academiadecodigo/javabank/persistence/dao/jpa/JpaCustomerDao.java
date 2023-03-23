package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.dao.CustomerDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class JpaCustomerDao implements CustomerDao {

    JpaSessionManager jpaSessionManager;
    JpaTransactionManager jpaTransactionManager;

    public JpaCustomerDao (JpaSessionManager jpaSessionManager){
        this.jpaSessionManager = jpaSessionManager;
    }

    @Override
    public List<Customer> findAll() {

        EntityManager em = jpaSessionManager.getCurrentSession();

        TypedQuery <Customer> query = em.createQuery("SELECT customer FROM Customer customer ", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(Integer id) {
          return jpaSessionManager.getCurrentSession().find(Customer.class, id);
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        return null;
    }

    @Override
    public Void delete(Integer id) {
        return null;
    }

    @Override
    public Customer findByUsername(String firstName) {
        return null;
    }

    @Override
    public Customer findByEmail(String email) {
        return null;
    }
}
