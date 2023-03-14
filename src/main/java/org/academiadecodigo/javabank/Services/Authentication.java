package org.academiadecodigo.javabank.Services;

import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.model.Customer;

import java.util.HashMap;

public class Authentication implements AuthService {

    LoginController loginController;

    CustomerServ customerServ;

    int validId;


    @Override
    public boolean authenticate(Integer id) {
        if (customerServ.get(id) != null) {
            validId = id;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Customer getAccessingCustomer() {
        return customerServ.get(validId);
    }

    public void setCustomerServ(CustomerServ customerServ) {
        this.customerServ = customerServ;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
