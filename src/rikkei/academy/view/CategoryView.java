package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.CategoryController;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.Category;
import rikkei.academy.model.User;

import java.util.List;

public class CategoryView {
    public static CategoryView categoryViewInstance;
    private UserController userController = new UserController();
    public static CategoryView getCategoryViewInstance(){
        if(categoryViewInstance==null){
            categoryViewInstance = new CategoryView();
        }
        return categoryViewInstance;
    }
    public CategoryView() {
        User userLogin = userController.getUserLogin();
        System.out.println("******************* CATEGORY MANAGE *******************");
        System.out.println("1. Show List Category");
        if(userLogin!=null){
            System.out.println("2. Create Category");
            System.out.println("3. Update Category");
            System.out.println("4. My Category");
        }
        int chooseMenu = Config.scanner().nextInt();
        switch (chooseMenu) {
            case 1:
               showFormCategoryList();
                break;
            case 2:
               formCreateCategory();
                break;
            case 3:
                formUpdateCategory();
                break;
            case 4:
                tableShowCategoryByUser();
                break;
        }
    }

    CategoryController categoryController = new CategoryController();
    List<Category> categoryList = categoryController.getListCategory();
    public void showFormCategoryList(){
        System.out.println("=================== TABLE CATEGORY ========================");
        System.out.println("====ID====NAME====");
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println("===="+categoryList.get(i).getId()+"===="+categoryList.get(i).getName());
        }
        System.out.println("Enter the back to return Menu: ");
        String backMenu = Config.scanner().nextLine();
        if(backMenu.equalsIgnoreCase("back")){
            new Navbar();
        }
    }
    public void formCreateCategory(){
        while (true){
            int id = 0;
            if(categoryList.size()==0){
                id = 1;
            }else {
                id = categoryList.get(categoryList.size()-1).getId()+1;
            }
            System.out.println("Enter the name: ");
            String name = Config.scanner().nextLine();
            Category category = new Category(id,name);
            categoryController.createCategoryToDB(category);
            System.out.println("Create Success!");
            System.out.println("Enter the any key to continue or Enter back to return Menu: ");
            String backMenu = Config.scanner().nextLine();
            if(backMenu.equalsIgnoreCase("back")){
                new Navbar();
                break;
            }
        }

    }
    public void formUpdateCategory(){
        while (true){
            System.out.println("Enter the id to update: ");
            int id = Config.scanner().nextInt();
            if(categoryController.detailCategory(id)==null){
                System.err.println("Id not found! Please try again!");
//                id = Config.scanner().nextInt();
            } else {
                System.out.println("Enter the name: ");
                String name = Config.scanner().nextLine();
                Category category = new Category(id, name);
                categoryController.updateCategory(category);
                System.out.println("Update success!");
                System.out.println("Enter the any key to continue or Enter back to return Menu: ");
                String backMenu = Config.scanner().nextLine();
                if(backMenu.equalsIgnoreCase("back")){
                    new Navbar();
                    break;
                }
            }
        }
    }
    public void tableShowCategoryByUser(){
        List<Category> listCategoryByUser = categoryController.getListCategoryByUser();
        for (int i = 0; i < listCategoryByUser.size(); i++) {
            System.out.println(listCategoryByUser.get(i).getName());
        }
    }
}
