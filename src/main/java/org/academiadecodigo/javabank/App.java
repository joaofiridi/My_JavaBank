package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaCustomerDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.jpa.JpaAccountService;
import org.academiadecodigo.javabank.services.jpa.JpaCustomerService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT);

        App app = new App();
        app.bootStrap(emf);

        //emf.close();

    }

    private void bootStrap(EntityManagerFactory emf) {

        Bootstrap bootstrap = new Bootstrap();
        JpaSessionManager jpaSessionManager = new JpaSessionManager();
        jpaSessionManager.setEmf(emf);

        JpaCustomerDao jpaCustomerDao = new JpaCustomerDao(jpaSessionManager);
        System.out.println(jpaCustomerDao.findById(1));

        // bootstrap.setAuthService(new AuthServiceImpl());
         bootstrap.setJpaCustomerDao(jpaCustomerDao);
        // bootstrap.setAccountService(new JpaAccountService(emf));
       // bootstrap.setCustomerService(new JpaCustomerService(emf));

        Controller controller = bootstrap.wireObjects();

        // start application
         controller.init();
    }
}
