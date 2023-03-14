package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.Services.Authentication;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.view.LoginView;
import org.academiadecodigo.javabank.view.MainView;

import java.util.HashMap;

/**
 * The {@link LoginView} controller
 */
public class LoginController extends AbstractController {

    private Controller nextController;

    @Override
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    private Authentication authentication;
    LoginView loginView;
    MainView mainView;
    private int loginCustomer;
    private HashMap<Integer, Customer> customers;


    /**
     * Sets the next controller
     *
     * @param nextController the next controller to be set
     */
    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }

    public Customer getLoginCustomer() {
        return customers.get(loginCustomer);
    }


    /**
     * Sets the bank
     *
     * @param bank the bank to be set
     */


    /**
     * Identifies the logged in customer
     *
     * @param id the customer id
     */
    public void onLogin(int id) {
        if (authentication.authenticate(id)) {
            nextController.init();
        } else {
            System.out.println("This customer does not exist");
            init();
        }


    }

}
