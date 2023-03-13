package org.academiadecodigo.javabank.View;

import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.Controller.UserMenuController;
import org.academiadecodigo.javabank.application.BankApplication;
import org.academiadecodigo.javabank.application.Messages;
import org.academiadecodigo.javabank.application.UserOptions;
import org.academiadecodigo.javabank.domain.Bank;

public class UserMenuView implements ViewInterface {


    UserMenuController userMenuController;

    BankApplication bankApplication;

    public void setBankApplication(BankApplication bankApplication) {
        this.bankApplication = bankApplication;
    }



    public void setUserMenuController (UserMenuController userMenuController) {
        this.userMenuController = userMenuController;
    }



    @Override
    public void show() {
        System.out.println("boass");

        MenuInputScanner mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);


       // IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getCustomerIds());
        //scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        //scanner.setError(Messages.ERROR_INVALID_CUSTOMER);

    }
}
