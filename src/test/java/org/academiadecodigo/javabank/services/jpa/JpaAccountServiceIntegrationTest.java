package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.CheckingAccount;
import org.academiadecodigo.javabank.persistence.JpaIntegrationTestHelper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JpaAccountServiceIntegrationTest extends JpaIntegrationTestHelper {

    private final static Integer INVALID_ID = 9999;
    private final static double DOUBLE_DELTA = 0.1;
    private JpaAccountService as;

    @Before
    public void setup() {
        as = new JpaAccountService(emf);
    }

    @Test
    public void testSave() {

        // setup
        AbstractAccount newAccount = new CheckingAccount();

        // exercise
        AbstractAccount addedAccount = as.save(newAccount);

        // verify
        assertNotNull("Account not added", addedAccount);
        AbstractAccount account = em.find(AbstractAccount.class, addedAccount.getId());
        assertNotNull("Account not found", account);

    }

    @Test
    public void testUpdateAccount() {

        // setup
        int id = 1;
        AbstractAccount account = em.find(AbstractAccount.class, id);
        account.credit(100);

        // exercise
        as.save(account);

        // verify
        account = em.find(AbstractAccount.class, id);
        assertEquals("Account balance is wrong", 200, account.getBalance(), DOUBLE_DELTA);

    }

    @Test
    public void testDepositCheckingAccount() {

        // setup
        int id = 1;

        // exercise
        as.deposit(id, 100);

        // verify
        AbstractAccount account = em.find(AbstractAccount.class, id);
        assertEquals("Account balance is wrong", 200, account.getBalance(), DOUBLE_DELTA);

    }

    @Test
    public void testDepositSavingsAccount() {

        // setup
        int id = 2;

        // exercise
        as.deposit(id, 100);

        // verify
        AbstractAccount account = em.find(AbstractAccount.class, id);
        assertEquals("Account balance is wrong", 150.5, account.getBalance(), DOUBLE_DELTA);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testDepositInvalidAccount() {
        as.deposit(INVALID_ID, 100);
    }

    @Test
    public void testWithdrawCheckingAccount() {

        // setup
        int id = 1;
        double amount = 50;

        // exercise
        as.withdraw(id, amount);

        // verify
        AbstractAccount account = em.find(AbstractAccount.class, id);
        assertEquals("Account balance is wrong", amount, account.getBalance(), DOUBLE_DELTA);

    }

    @Test
    public void testEmptyCheckingAccount() {

        // setup
        int id = 1;

        // exercise
        as.withdraw(id, 100);

        // verify
        AbstractAccount account = em.find(AbstractAccount.class, id);
        assertEquals("Account balance is wrong", 0, account.getBalance(), DOUBLE_DELTA);

    }

    @Test
    public void testWithdrawCheckingInsufficientFunds() {

        // setup
        int id = 1;

        // exercise
        as.withdraw(id, 150);

        // verify
        AbstractAccount account = em.find(AbstractAccount.class, id);
        assertEquals("Account balance is wrong", 100, account.getBalance(), DOUBLE_DELTA);

    }

    @Test
    public void testWithdrawSavingsAccount() {

        // setup
        int id = 4;

        // exercise
        as.withdraw(id, 20);

        // verify
        AbstractAccount account = em.find(AbstractAccount.class, id);
        assertEquals("Account balance is wrong", 130, account.getBalance(), DOUBLE_DELTA);

    }

    @Test
    public void testWithdrawMaxSavingsAccount() {

        // setup
        int id = 4;

        // exercise
        as.withdraw(id, 50);

        // verify
        AbstractAccount account = em.find(AbstractAccount.class, id);
        assertEquals("Account balance is wrong", 100, account.getBalance(), DOUBLE_DELTA);

    }

    @Test
    public void testWithdrawSavingsAccountInsufficientFunds() {

        // setup
        int id = 4;

        // exercise
        as.withdraw(id, 100);

        // verify
        AbstractAccount account = em.find(AbstractAccount.class, id);
        assertEquals("Account balance is wrong", 150, account.getBalance(), DOUBLE_DELTA);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawInvalidAccount() {
        as.withdraw(INVALID_ID, 100);
    }

    @Test
    public void testTransfer() {

        // exercise
        as.transfer(1, 2, 10.5);

        // verify
        AbstractAccount srcAccount = em.find(AbstractAccount.class, 1);
        AbstractAccount dstAccount = em.find(AbstractAccount.class, 2);
        assertEquals("Source Account balance is wrong", 89.5, srcAccount.getBalance(), DOUBLE_DELTA);
        assertEquals("Destination Account balance is wrong", 61, dstAccount.getBalance(), DOUBLE_DELTA);

    }

    @Test
    public void testTransferInsufficientFunds() {

        // exercise
        as.transfer(1, 2, 200);

        // verify
        AbstractAccount srcAccount = em.find(AbstractAccount.class, 1);
        AbstractAccount dstAccount = em.find(AbstractAccount.class, 2);
        assertEquals("Source Account balance is wrong", 100, srcAccount.getBalance(), DOUBLE_DELTA);
        assertEquals("Destination Account balance is wrong", 50.5, dstAccount.getBalance(), DOUBLE_DELTA);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testTransferInvalidSrcAccount() {
        as.transfer(INVALID_ID, 2, 200);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTransferInvalidDstAccount() {
        as.transfer(1, INVALID_ID, 200);
    }
}
