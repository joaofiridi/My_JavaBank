package org.academiadecodigo;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.Config;
import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.controller.MainController;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaCustomerDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;
import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.jpa.JpaCustomerService;
import org.academiadecodigo.javabank.view.LoginView;
import org.academiadecodigo.javabank.view.MainView;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainDao {
    public static void main(String[] args) {

        JpaSessionManager jpaSessionManager = new JpaSessionManager();
        JpaCustomerDao jpaCustomerDao = new JpaCustomerDao(jpaSessionManager);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT);
        jpaSessionManager.setEmf(emf);

        AuthServiceImpl authService = new AuthServiceImpl();



        // wire services
        authService.setJpaCustomerDao(jpaCustomerDao);
        Prompt prompt = new Prompt(System.in, System.out);

        // wire login controller and view
        LoginController loginController = new LoginController();
        LoginView loginView = new LoginView();
        loginController.setView(loginView);
        loginController.setAuthService(authService);
        loginView.setLoginController(loginController);
        loginView.setPrompt(prompt);
        System.out.println(jpaCustomerDao.findById(2));
        loginController.init();

        // wire main controller and view
        MainController mainController = new MainController();
        MainView mainView = new MainView();
        mainView.setPrompt(prompt);
        mainView.setMainController(mainController);
        mainController.setView(mainView);
        mainController.setAuthService(authService);
        loginController.setNextController(mainController);




    }
}
