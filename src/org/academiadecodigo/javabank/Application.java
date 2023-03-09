package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;

public class Application {

    Bank bank;
    int id;

    private Prompt prompt = new Prompt(System.in, System.out);
    public Application (Bank bank){
        this.bank = bank;
    }

    public String[] menu = {
            "View Balance",
            "Make Deposit",
            "Make Withdraw",
            "Open Account",
            "Quit"
    };

    public void checkCustomerID(){

        IntegerInputScanner idQuestion = new IntegerInputScanner();
        idQuestion.setMessage("Hello! \n What is your id? \n");

        id = prompt.getUserInput(idQuestion);

        // este id é valido??
        if(bank.getCustomers().containsKey(id)){
            System.out.println("Welcome!");
            menu();
        } else {
            checkCustomerID();
        }

    }

    public void menu(){
        MenuInputScanner menuInputScanner = new MenuInputScanner(menu);
        int answer = prompt.getUserInput(menuInputScanner);

        switch (answer) {
            case 1:
                Customer customer = bank.getCustomers().get(id);
                System.out.println("Your total balance is: " + customer.getBalance());
                menu();
                break;
            case 2:
                System.out.println("2 !!!!!!!!!!!!");
                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
        }






    }
}
