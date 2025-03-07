package com.example.todolist.services;

import com.example.todolist.models.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getAllCategories();
    public Category createCategory(Category category);

}
