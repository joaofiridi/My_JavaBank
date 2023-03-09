import org.academiadecodigo.javabank.Application;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.managers.AccountManager;

public class Main {
    public static void main(String[] args) {


        Customer c1 = new Customer(1, "João");
        Customer c2 = new Customer(2, "Diogo");
        AccountManager accountManager = new AccountManager();

        c1.setAccountManager(accountManager);
        c2.setAccountManager(accountManager);

        Bank bank = new Bank(accountManager);
        bank.addCustomer(c1);
        bank.addCustomer(c2);

        Application app = new Application(bank);
        app.checkCustomerID();
       // Menu menu = new Menu();
       // menu.menuChoice();
       // menu.setMenu();



    }
}
