package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.Services.AccountServ;
import org.academiadecodigo.javabank.Services.Authentication;
import org.academiadecodigo.javabank.Services.CustomerServ;
import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.controller.MainController;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.view.LoginView;
import org.academiadecodigo.javabank.view.MainView;

public class Testing {
    public static void main(String[] args) {

        Prompt prompt = new Prompt(System.in, System.out);
        CustomerServ customerServ = new CustomerServ();
        AccountServ accountServ = new AccountServ();
        Authentication authentication = new Authentication();
        LoginController loginController = new LoginController();
        LoginView loginView = new LoginView();
        MainController mainController = new MainController();
        MainView mainView = new MainView();

        Customer c1 = new Customer(1,"João");
        Customer c2 = new Customer(2,"Cris");


        customerServ.add(c1);
        customerServ.add(c2);

        authentication.setLoginController(loginController);
        authentication.setCustomerServ(customerServ);
        customerServ.setAuthentication(authentication);

        loginController.setAuthentication(authentication);
        loginController.setView(loginView);
        loginView.setPrompt(prompt);
        loginView.setLoginController(loginController);

        loginController.setNextController(mainController);
        mainController.setView(mainView);
        mainView.setCustomerServ(customerServ);
        customerServ.setMainView(mainView);
        mainView.setPrompt(prompt);
        mainController.setPrompt(prompt);
        mainController.setMainView(mainView);
        mainView.setMainController(mainController);


        loginController.init();



    }
}
