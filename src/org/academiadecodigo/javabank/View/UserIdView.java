package org.academiadecodigo.javabank.View;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.javabank.Controller.UserIdController;
import org.academiadecodigo.javabank.application.Messages;
import org.academiadecodigo.javabank.domain.Bank;

public class UserIdView implements ViewInterface {


    UserIdController userIdController;
    Prompt prompt;

    Bank bank;
    IntegerSetInputScanner scanner;

    int id;

    public void setUserIdController(UserIdController userIdController) {
        this.userIdController = userIdController;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public int getId(){
        return id;
    }




    @Override
    public void show() {
        //userIdController.setScanner(scanner);
        scanner = new IntegerSetInputScanner(bank.getCustomerIds());
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);
        id =  prompt.getUserInput(scanner);


    }
}
