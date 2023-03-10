package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.Account;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.domain.account.CheckingAccount;
import org.academiadecodigo.javabank.domain.account.SavingsAccount;
import org.academiadecodigo.javabank.managers.AccountManager;

public class Application {

    Bank bank;
    AccountManager accountManager;


    int id;
    int accID;
    boolean isCheckingAcc = false;
    boolean isSavingsAcc = false;
    boolean isAccountOpen = false;


    private Prompt prompt = new Prompt(System.in, System.out);

    public Application(Bank bank) {
        this.bank = bank;
    }

    public String[] menu = {
            "View Balance",
            "Make Deposit",
            "Make Withdraw",
            "Open Account",
            "Quit"
    };

    public void checkCustomerID() {

        IntegerInputScanner idQuestion = new IntegerInputScanner();
        idQuestion.setMessage("Hello! \n What is your id? \n");

        id = prompt.getUserInput(idQuestion);

        // is the ID valid ?
        if (bank.getCustomers().containsKey(id)) {
            System.out.println("Welcome!");
            menu();
        } else {
            checkCustomerID();
        }

    }


    public void menu() {
        MenuInputScanner menuInputScanner = new MenuInputScanner(menu);
        int answer = prompt.getUserInput(menuInputScanner);


        switch (answer) {
            case 1:
                Customer customer = bank.getCustomers().get(id);
                System.out.println("Your total balance is: " + customer.getBalance());
                menu();
                break;

            case 2:
                if (!isAccountOpen) {
                    System.out.println("You don´t have any account. Create one first.");
                    menu();
                } else if (isAccountOpen) {
                    String[] accType = {
                            "SAVINGS",
                            "CHECKING"
                    };
                    MenuInputScanner accTypeScanner = new MenuInputScanner(accType);
                    accTypeScanner.setMessage("In which account?");
                    int accTypeAnswer = prompt.getUserInput(accTypeScanner);

                    if ((accTypeAnswer == 1 && isSavingsAcc) || (accTypeAnswer == 2 && isCheckingAcc)) {
                        IntegerInputScanner amount = new IntegerInputScanner();
                        amount.setMessage("How much do you want to deposit? \n");
                        int amountCredit = prompt.getUserInput(amount);

                        accountManager = new AccountManager();
                        System.out.println("aqui");
                        accountManager.deposit(accID, amountCredit); // ERRO <-----------------------
                        System.out.println("Credit done.");
                        menu();
                    }

                }
                System.out.println("You didnt open that type of account.");
                menu();
                break;
            case 3:

                menu();
                break;
            case 4:
                String[] accType = {
                        "SAVINGS",
                        "CHECKING"
                };

                MenuInputScanner accTypeScanner = new MenuInputScanner(accType);
                accTypeScanner.setMessage("Which type of account do you want to open?");
                int accTypeAnswer = prompt.getUserInput(accTypeScanner);


                if (accTypeAnswer == 1) {
                    customer.openAccount(AccountType.SAVINGS);
                    Account account = new SavingsAccount(accID);
                    accID = account.getId();
                   isAccountOpen = true;
                    isSavingsAcc = true;
                    accID++;

                } else if (accTypeAnswer == 2) {
                    customer.openAccount(AccountType.CHECKING);
                    Account account = new CheckingAccount(accID);
                    accID = account.getId();
                    isAccountOpen = true;
                    isCheckingAcc = true;
                    accID++;
                }
                System.out.println("Your account has been created! ID: " + accID);
                menu();

                break;
            case 5:
                break;
        }
    }

}
