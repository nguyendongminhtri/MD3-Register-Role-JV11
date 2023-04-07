package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.dto.request.SignInDTO;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.dto.response.ResponseMessage;
import rikkei.academy.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserView {
    public static UserView userViewInstance;
    public static UserView getUserViewInstance(){
        if(userViewInstance==null){
            userViewInstance = new UserView();
        }
        return userViewInstance;
    }
    UserController userController = new UserController();
    List<User> userList = userController.getListUser();
    public void formRegister() {
        System.out.println("size -->"+userList.size());
        int id = 0;
        if (userList.size() == 0) {
            id = 1;
        } else {
           id = userList.get(userList.size()-1).getId()+1;
        }
        System.out.println("id =---"+id);
        System.out.println("Enter the name: ");
        String name = Config.scanner().nextLine();
        System.out.println("Enter the username: ");
        String username = Config.scanner().nextLine();
        System.out.println("Enter the email: ");
        String email = Config.scanner().nextLine();
        System.out.println("Enter the password: ");
        String password = Config.scanner().nextLine();
        System.out.println("Enter the role: ");
        String role = Config.scanner().nextLine();
        Set<String> strRole = new HashSet<>();
        strRole.add(role);
        SignUpDTO sign = new SignUpDTO(id,name,username,email,password,strRole);
        while (true){
            ResponseMessage responseMessage = userController.register(sign);
          if(responseMessage.getMessage().equals("user_existed")){
              System.err.println("user name existed!");
              username = Config.scanner().nextLine();
              sign.setUsername(username);
              System.out.println("duoi lenh nhap");
          } else if(responseMessage.getMessage().equals("email_existed")){
              System.err.println("email name existed!");
              email = Config.scanner().nextLine();
              sign = new SignUpDTO(id,name,username,email,password,strRole);
          } else if(responseMessage.getMessage().equals("create_success")){
              formLogin();
              break;
          }
        }
    }
    public  void formLogin(){
        System.out.println("Form Login!");
        System.out.println("Enter your username: ");
        String username = Config.scanner().nextLine();
        System.out.println("Enter your password: ");
        String password = Config.scanner().nextLine();
        SignInDTO signInDTO = new SignInDTO(username,password);
        while (true) {
            ResponseMessage responseMessage = userController.login(signInDTO);
            if(responseMessage.getMessage().equals("login_failed")){
                System.err.println("Login failed! Please check your account!");
                System.out.println("Enter your username: ");
                username = Config.scanner().nextLine();
                System.out.println("Enter your password: ");
                password = Config.scanner().nextLine();
                signInDTO.setUsername(username);
                signInDTO.setPassword(password);
            } else {
                System.out.println("LOGIN SUCCESS!");
                new Navbar();
                break;
            }
        }
    }
    public void showListUser(){
        System.out.println(userController.getListUser());
        System.out.println("Enter back to return Navbar: ");
        String back = Config.scanner().nextLine();
        if(back.equalsIgnoreCase("back")){
            new Navbar();
        }
    }
}
