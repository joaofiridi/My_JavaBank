package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.persistence.TransactionManager;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaAccountDao;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaCustomerDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;
import org.academiadecodigo.javabank.persistence.jpa.JpaTransactionManager;
import org.academiadecodigo.javabank.services.AccountServiceImpl;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.CustomerServiceImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT);

        JpaSessionManager sm = new JpaSessionManager(emf);
        TransactionManager tx = new JpaTransactionManager(sm);

        App app = new App();
        app.bootStrap(tx, sm);

        emf.close();

    }

    private void bootStrap(TransactionManager tx, JpaSessionManager sm) {

        AccountServiceImpl accountService = new AccountServiceImpl();
        accountService.setAccountDao(new JpaAccountDao(sm));
        accountService.setTransactionManager(tx);

        CustomerServiceImpl customerService = new CustomerServiceImpl();
        customerService.setCustomerDao(new JpaCustomerDao(sm));
        customerService.setTransactionManager(tx);

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(accountService);
        bootstrap.setCustomerService(customerService);

        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();
    }
}
