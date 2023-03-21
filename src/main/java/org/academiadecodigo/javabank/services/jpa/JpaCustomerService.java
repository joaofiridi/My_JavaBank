package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.AbstractAccount;
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
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * @see CustomerService#getBalance(Integer)
     */
    @Override
    public double getBalance(Integer id) {

        EntityManager em = emf.createEntityManager();

        try {

            Customer customer = Optional.ofNullable(em.find(Customer.class, id))
                .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

            return customer.getAccounts().stream()
                .mapToDouble(AbstractAccount::getBalance)
                .sum();

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
                .map(AbstractModel::getId)
                .collect(Collectors.toSet());

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
