package rikkei.academy.service;

import rikkei.academy.model.Category;

import java.util.List;

public interface ICategoryService extends IGenericService<Category>{
    List<Category> findByUser();
}
