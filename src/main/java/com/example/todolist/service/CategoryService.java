package com.example.todolist.service;

import com.example.todolist.model.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getAllCategories();
    public Category createCategory(Category category);

}
