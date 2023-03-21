package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.persistence.JpaIntegrationTestHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class JpaCustomerServiceIntegrationTest extends JpaIntegrationTestHelper {

    private final static Integer INVALID_ID = 9999;
    private final static double DOUBLE_DELTA = 0.1;

    private JpaCustomerService cs;

    @Before
    public void setUp() {

        cs = new JpaCustomerService(emf);
    }

    @Test
    public void testGetBalance() {

        // exercise
        double result = cs.getBalance(1);

        // verify
        assertEquals("The balance is different from what was expected", 150.5, result, DOUBLE_DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBalanceInvalidCustomer() {

        cs.getBalance(INVALID_ID);

    }

    @Test
    public void testListCustomerAccountIds() {

        // exercise
        Set<Integer> ids = cs.listCustomerAccountIds(1);

        // verify
        assertNotNull("Set is null", ids);
        assertEquals("Not the number of users expected", 2, ids.size());
        assertEquals("It should not be empty", false, ids.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCustomerAccountIdsInvalidCustomer() {

        cs.listCustomerAccountIds(INVALID_ID);
    }

    @Test
    public void testGet() {

        // setup
        int id = 1;

        // exercise
        Customer customer = cs.get(id);

        // verify
        assertNotNull("Customer is null", customer);
        assertEquals("Customer id is wrong", id, customer.getId().intValue());
        assertEquals("Customer name is wrong", "Rui", customer.getName());

    }

    @Test()
    public void testGetInvalidCustomer() {
        Customer customer = cs.get(INVALID_ID);
        assertNull("invalid customer should not be found", customer);
    }
}
