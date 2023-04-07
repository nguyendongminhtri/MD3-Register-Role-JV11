package rikkei.academy.service;

import rikkei.academy.config.Config;
import rikkei.academy.model.Category;
import rikkei.academy.model.User;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceIMPL implements ICategoryService {
    List<Category> categoryList = new Config<Category>().readFromFile(Config.PATH_CATEGORY);
    private IUserService userService = new UserServiceIMPL();
    @Override
    public List<Category> findAll() {
        return categoryList;
    }

    @Override
    public void save(Category category) {
        User user = userService.getCurentUser();
        if(findById(category.getId())==null){
            category.setUser(user);
            categoryList.add(category);
        } else {
            categoryList.set(categoryList.indexOf(findById(category.getId())),category);
        }
        new Config<Category>().writeToFile(Config.PATH_CATEGORY, categoryList);
    }

    @Override
    public Category findById(int id) {
        for (int i = 0; i < categoryList.size(); i++) {
            if(categoryList.get(i).getId()==id){
                return categoryList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Category> findByUser() {
        User user = userService.getCurentUser();
        List<Category> categoryByUser = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            if(categoryList.get(i).getUser().getId()==user.getId()){
                categoryByUser.add(categoryList.get(i));
            }
        }
        return categoryByUser;
    }
}
