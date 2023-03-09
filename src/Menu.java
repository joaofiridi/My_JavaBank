import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.domain.Customer;

public class Menu {
    Methods methods = new Methods();

    public Prompt prompt = new Prompt(System.in, System.out);
    public String[] menu = {
            "View Balance",
            "Make Deposit",
            "Make Withdraw",
            "Open Account",
            "Quit"
    };

    public void setMenu() {
        while(true) {
            methods.setMenu(this);
            menuChoice();
        }
    }


    public void menuChoice() {
        MenuInputScanner menuInputScanner = new MenuInputScanner(menu);
        int answer = prompt.getUserInput(menuInputScanner);

        switch (answer) {
            case 1:
               // methods.checkID();
                break;
            case 2:
                System.out.println("2 !!!!!!!!!!!!");
                break;
            case 3:

                break;
            case 4:
                methods.openAccount();
                break;
            case 5:

                break;
        }
    }


}
