package org.academiadecodigo.javabank.Controller;

import org.academiadecodigo.javabank.View.UserMenuView;

public class UserMenuController implements ControllerInterface {


    UserMenuView userMenuView;

    UserIdController userIdController;

    public void setUserIdController(UserIdController userIdController) {
        this.userIdController = userIdController;
    }


    public void setUserMenuView (UserMenuView userMenuView) {
        this.userMenuView = userMenuView;
    }


    @Override
    public void init() {
        System.out.println("sdfsdfds");
        userMenuView.show();

    }
}
