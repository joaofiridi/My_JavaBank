import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.javabank.domain.Bank;


public class Methods {


    Bank bank;
    Menu menu;
    public boolean hasID;
    int id;


    Prompt prompt = new Prompt(System.in, System.out);

    /*
    public boolean checkID() {
        IntegerInputScanner askID = new IntegerInputScanner();
        askID.setMessage("Please write your ID \n");

        id = prompt.getUserInput(askID);
        hasID = false;

        try {
            if (bank.getCustomers().contains(id)) {
                System.out.println("entrei no try??????");
                hasID = true;
            }
        }catch (Exception exception){
            System.out.println("This ID does not exist, please create an account first");
        }
        setMenu(menu);

        return hasID;


    }*/

    public void viewBalance() {
        System.out.println("Account + ");
    }

    public void openAccount(){

        /*
        if (!hasID){
            StringInputScanner setName = new StringInputScanner();
            setName.setMessage("Tell us your name: \n");
            name = prompt.getUserInput(setName);

            customer = new Customer(id, name);
            System.out.println("Your account has been created! Acc: \n id: "+ id + " name: "+ name);

            accountManager = new AccountManager();
            bank = new Bank(accountManager);
            bank.addCustomer(customer);

            hasID = true;
            System.out.println("Your account has been created!");
            setMenu(menu);
        }*/
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
