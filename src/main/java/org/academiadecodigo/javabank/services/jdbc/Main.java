package org.academiadecodigo.javabank.services.jdbc;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.*;
import org.academiadecodigo.javabank.controller.transaction.DepositController;
import org.academiadecodigo.javabank.controller.transaction.WithdrawalController;
import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.view.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("javabank");
        CustomerJDA customerJDA = new CustomerJDA();
        AccountFactory accountFactory = new AccountFactory();
        AccountJDA accountJDA = new AccountJDA();

        customerJDA.setEmf(emf);
        Customer c1 = new Customer("Joao","Diogo","yoyoyo@gmail.com","912345679");
        Customer c2 = new Customer("Cristiano","Esteves","CR7@gmail.com","911234567");
        System.out.println(customerJDA.get(2));



        // wire services

        Prompt prompt = new Prompt(System.in, System.out);
        AuthServiceImpl authService = new AuthServiceImpl();
        authService.setCustomerService(customerJDA);
        LoginController loginController = new LoginController();
        LoginView loginView = new LoginView();
        loginController.setView(loginView);
        loginController.setAuthService(authService);
        loginView.setLoginController(loginController);
        loginView.setPrompt(prompt);

        // wire main controller and view
        MainController mainController = new MainController();
        MainView mainView = new MainView();
        mainView.setPrompt(prompt);
        mainView.setMainController(mainController);
        mainController.setView(mainView);
        mainController.setAuthService(authService);
        loginController.setNextController(mainController);

        // wire balance controller and view
        BalanceController balanceController = new BalanceController();
        BalanceView balanceView = new BalanceView();
        balanceView.setBalanceController(balanceController);
        balanceController.setView(balanceView);
        balanceController.setCustomerService(customerJDA);
        balanceController.setAuthService(authService);

        // wire new account controller and view
        NewAccountView newAccountView = new NewAccountView();
        NewAccountController newAccountController = new NewAccountController();
        newAccountController.setAccountFactory(accountFactory);
        newAccountController.setAccountService(accountJDA);
        newAccountController.setAuthService(authService);
        newAccountController.setView(newAccountView);
        newAccountView.setNewAccountController(newAccountController);

        // wire account transactions controllers and views
        DepositController depositController = new DepositController();
        WithdrawalController withdrawalController = new WithdrawalController();
        AccountTransactionView depositView = new AccountTransactionView();
        AccountTransactionView withdrawView = new AccountTransactionView();
        depositController.setAuthService(authService);
        depositController.setAccountService(accountJDA);
        depositController.setCustomerService(customerJDA);
        depositController.setView(depositView);
        withdrawalController.setAuthService(authService);
        withdrawalController.setCustomerService(customerJDA);
        withdrawalController.setAccountService(accountJDA);
        withdrawalController.setView(withdrawView);
        depositView.setPrompt(prompt);
        depositView.setTransactionController(depositController);
        withdrawView.setPrompt(prompt);
        withdrawView.setTransactionController(withdrawalController);

        // setup the controller map
        Map<Integer, Controller> controllerMap = new HashMap<>();
        controllerMap.put(UserOptions.GET_BALANCE.getOption(), balanceController);
        controllerMap.put(UserOptions.OPEN_ACCOUNT.getOption(), newAccountController);
        controllerMap.put(UserOptions.DEPOSIT.getOption(), depositController);
        controllerMap.put(UserOptions.WITHDRAW.getOption(), withdrawalController);

        mainController.setControllerMap(controllerMap);
        loginController.init();


        //customerJDA.add(c1);
        //customerJDA.add(c2);
        customerJDA.get(1);



    }
}
