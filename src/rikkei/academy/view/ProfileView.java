package rikkei.academy.view;

import rikkei.academy.controller.UserController;
import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProfileView {
    UserController userController = new UserController();
    public ProfileView() {
        User user = userController.getUserLogin();
        if(user!=null){
            Set<Role> roleSet = user.getRoles();
            List<Role> roles = new ArrayList<>(roleSet);
            if(roles.get(0).getName()== RoleName.ADMIN){
                System.out.println("PHẦN DÀNH CHO ADMIN");
            } else if(roles.get(0).getName()==RoleName.USER){
                System.out.println("PHẦN DÀNH CHO USER");
            }
        }
    }
}
