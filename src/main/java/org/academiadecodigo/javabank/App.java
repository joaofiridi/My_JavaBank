package org.academiadecodigo.javabank;

import org.academiadecodigo.JDBC.ConnectionMaster;
import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.services.AccountServiceImpl;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.jdbc.CustomerJDBC;

public class App {

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();
    }

    private void bootStrap() {


       Bootstrap bootstrap = new Bootstrap();
       ConnectionMaster connectionMaster = new ConnectionMaster();
        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(new AccountServiceImpl());
        CustomerJDBC customerJDBC = new CustomerJDBC();
        customerJDBC.setConnectionMaster(connectionMaster);
        bootstrap.setCustomerJDBC(customerJDBC);

        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();
    }
}
