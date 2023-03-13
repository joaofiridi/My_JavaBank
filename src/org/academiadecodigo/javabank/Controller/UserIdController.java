package org.academiadecodigo.javabank.Controller;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.javabank.View.UserIdView;
import org.academiadecodigo.javabank.domain.Bank;

public class UserIdController implements ControllerInterface {

    private Bank bank;
    private UserIdView userIdView;

    private IntegerSetInputScanner scanner;



    private  Prompt prompt;

    private int idNumber;

    private int answer=0;

    UserMenuController userMenuController;
    public void setUserMenuController(UserMenuController userMenuController) {
        this.userMenuController = userMenuController;
    }



    public void setUserIdView(UserIdView userIdView) {
        this.userIdView = userIdView;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setScanner(IntegerSetInputScanner scanner) {
        this.scanner = scanner;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }


    public void init() {
        userIdView.show();

        answer = userIdView.getId();
        System.out.println("Welcome!");
        userMenuController.init();


    }



}
