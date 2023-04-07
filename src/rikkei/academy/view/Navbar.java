package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.Singleton;
import rikkei.academy.model.User;

import java.sql.SQLOutput;


public class Navbar {
    UserController userController = new UserController();
    public Navbar() {
        User user = userController.getUserLogin();
        if(user!=null){
            System.out.println("WELCOME "+user.getName());
            System.out.println("1. GO TO PROFILE");
            System.out.println("4. CATEGORY MANAGE");
            int chooseMenu = Config.scanner().nextInt();
            switch (chooseMenu){
                case 1:
                    new ProfileView();
                    break;
                case 4:
                    CategoryView.getCategoryViewInstance();
                    break;
            }
        }else {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. ShowList User");
            int chooseMenu = Config.scanner().nextInt();
            switch (chooseMenu){
                case 1:
                    UserView.getUserViewInstance().formRegister();
                    break;
                case 2:
                    UserView.getUserViewInstance().formLogin();
                    break;
                case 3:
                    UserView.getUserViewInstance().showListUser();
                    break;
            }
        }

    }

    public static void main(String[] args) {
        new Navbar();
//        Singleton singleton1 = new Singleton();
//        Singleton singleton2 = new Singleton();
//        Singleton singleton3 = Singleton.getInstance();
//        Singleton singleton4 = Singleton.getInstance();
    }
}