package com.example.todolist.controllers;

import com.example.todolist.models.Category;
import com.example.todolist.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping
    public  Category createCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }
}
