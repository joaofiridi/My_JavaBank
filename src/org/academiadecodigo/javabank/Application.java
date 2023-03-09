package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.domain.account.SavingsAccount;
import org.academiadecodigo.javabank.managers.AccountManager;

public class Application {

    Bank bank;
    AccountManager accountManager;

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
               IntegerInputScanner amount = new IntegerInputScanner();
               amount.setMessage("How much do you want to deposit? \n");

                Integer amountCredit = prompt.getUserInput(amount);
                accountManager = new AccountManager();
                System.out.println("aqui");
                accountManager.deposit(id, amountCredit);

                System.out.println("Credit done.");
                menu();

                break;
            case 3:

                menu();
                break;
            case 4:

                menu();
                break;
            case 5:
                break;
        }






    }
}
