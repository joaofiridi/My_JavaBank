package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.Model;
import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaCustomerDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;
import org.academiadecodigo.javabank.services.CustomerService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A JPA {@link CustomerService} implementation
 */
public class JpaCustomerService implements CustomerService {

    private EntityManagerFactory emf;
    private JpaTransactionManager jpaTransactionManager;
    private JpaCustomerDao jpaCustomerDao;

    /**
     * Instantiates a JpaCustomerService
     *
     * @param emf the {@link EntityManagerFactory} to use;
     */
    public JpaCustomerService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Customer get(Integer id) {
        jpaTransactionManager.beginWrite();
        jpaTransactionManager.rollback();
        return jpaCustomerDao.findById(id);
    }

    /**
     * @see CustomerService#getBalance(Integer)
     */
    @Override
    public double getBalance(Integer id) {

        EntityManager em = emf.createEntityManager();

        try {

            Customer customer = Optional.ofNullable(em.find(Customer.class, id)).orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

            double total = 0;
            for (Account account : customer.getAccounts()) {
                total += account.getBalance();
            }

            return total;

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * @see CustomerService#listCustomerAccountIds(Integer)
     */
    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {

        EntityManager em = emf.createEntityManager();

        try {

            Customer customer = Optional.ofNullable(em.find(Customer.class, id))
                .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

            return customer.getAccounts().stream()
                .map(Model::getId)
                .collect(Collectors.toSet());

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
