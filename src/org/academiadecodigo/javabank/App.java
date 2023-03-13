package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.Controller.UserIdController;
import org.academiadecodigo.javabank.View.UserIdView;
import org.academiadecodigo.javabank.Controller.UserMenuController;
import org.academiadecodigo.javabank.View.UserMenuView;
import org.academiadecodigo.javabank.application.BankApplication;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.managers.AccountManager;

public class App {

    public static void main(String[] args) {


        Bank bank = new Bank();
        AccountManager accountManager = new AccountManager();
        bank.setAccountManager(accountManager);

        Customer c1 = new Customer(1, "Rui");
        Customer c2 = new Customer(2, "Sergio");
        Customer c3 = new Customer(3, "Bruno");
        bank.addCustomer(c1);
        bank.addCustomer(c2);
        bank.addCustomer(c3);

        BankApplication bankApplication = new BankApplication(bank);
        //bankApplication.start();

        Prompt prompt = new Prompt(System.in, System.out);
        UserIdController userIdController = new UserIdController();
        UserIdView userIdView = new UserIdView();


        userIdView.setBank(bank);
        userIdController.setBank(bank);

        userIdController.setUserIdView(userIdView);
        userIdView.setUserIdController(userIdController);


        userIdView.setPrompt(prompt);
        userIdController.setPrompt(prompt);
        user


        UserMenuController userMenuController = new UserMenuController();
        UserMenuView userMenuView = new UserMenuView();

        userMenuController.setUserMenuView(userMenuView);
        userMenuView.setUserMenuController(userMenuController);
        userMenuView.setBankApplication(bankApplication);


        userIdController.setUserMenuController(userMenuController);


        userMenuController.setUserIdController(userIdController);




        userIdController.init();


    }
}
