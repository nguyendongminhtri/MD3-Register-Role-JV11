package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.User;

import java.sql.SQLOutput;


public class Navbar {
    UserController userController = new UserController();
    public Navbar() {
        User user = userController.getUserLogin();
        if(user!=null){
            System.out.println("WELCOME "+user.getName());
            System.out.println("1. GO TO PROFILE");
            int chooseMenu = Config.scanner().nextInt();
            switch (chooseMenu){
                case 1:
                    new ProfileView();
                    break;
            }
        }else {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. ShowList User");
            int chooseMenu = Config.scanner().nextInt();
            switch (chooseMenu){
                case 1:
                    new UserView().formRegister();
                    break;
                case 2:
                    new UserView().formLogin();
                    break;
                case 3:
                    new UserView().showListUser();
                    break;
            }
        }

    }

    public static void main(String[] args) {
        new Navbar();

    }
}