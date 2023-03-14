package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.Services.CustomerServ;
import org.academiadecodigo.javabank.controller.MainController;

/**
 * A view used to display the main menu
 *
 * @see MainController
 */
public class MainView extends AbstractView {

    public void setCustomerServ(CustomerServ customerServ) {
        this.customerServ = customerServ;
    }

    private MainController mainController;
    CustomerServ customerServ;

    /**
     * Sets the controller responsible for rendering the view
     *
     * @param mainController the controller to set
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * @see View#show()
     */
    @Override
    public void show() {
        showMenu();
    }

    private void showMenu() {

        MenuInputScanner scanner = new MenuInputScanner(UserOptions.getMessages());
        scanner.setError(Messages.VIEW_MAIN_ERROR);
        scanner.setMessage("\n" + Messages.VIEW_MAIN_MESSAGE);
                //+ customerServ.getLoginCustomer().getName());
        mainController.onMenuSelection(prompt.getUserInput(scanner));
    }
}
