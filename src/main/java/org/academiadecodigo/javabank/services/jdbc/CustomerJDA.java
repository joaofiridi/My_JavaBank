package org.academiadecodigo.javabank.services.jdbc;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.services.AccountService;
import org.academiadecodigo.javabank.services.CustomerService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

public class CustomerJDA implements CustomerService {

    private AccountService accountService;
    private EntityManagerFactory emf;



    @Override
    public Customer get(Integer id) {
        Customer customer = null;
        EntityManager em = emf.createEntityManager();

        try{
           // customer = em.find(Customer.class, id);
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery <Customer> criteriaQuery = builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.get("id"),id));
            customer = em.createQuery(criteriaQuery).getSingleResult();

        } catch (RollbackException exception){
            em.getTransaction().rollback();

        } finally {
            em.close();
        } return customer;
    }

    @Override
   public List<Customer> list() {
       EntityManager em = emf.createEntityManager();
        List<Customer> customerMap = new ArrayList<>();
        try {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery <Customer> criteriaQuery = builder.createQuery(Customer.class);
            Root<Customer> root = criteriaQuery.from(Customer.class);
            criteriaQuery.select(root);
           // criteriaQuery.where(builder.equal(root.get("name"),c))

        } catch (Exception e){
            System.out.println("dfsf");
        }
        return customerMap;
    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {
        return null;
    }

    @Override
    public double getBalance(int id) {
        EntityManager em = emf.createEntityManager();
        Customer customer = Optional.ofNullable(em.find(Customer.class,id)).orElseThrow(() -> new IllegalArgumentException());
        double total = 0;
        return total;
    }

    @Override
    public void add(Customer customer) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } catch (RollbackException exception){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
}
