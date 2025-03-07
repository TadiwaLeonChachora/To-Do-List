package com.example.todolist.services;

import com.example.todolist.models.Category;
import com.example.todolist.models.Todo;
import com.example.todolist.repositories.CategoryRepo;
import com.example.todolist.repositories.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepo todoRepo;
    private final CategoryRepo categoryRepo;

    @Autowired
    public TodoServiceImpl(TodoRepo todoRepo, CategoryRepo categoryRepo){
        this.todoRepo = todoRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepo.findAll();
    }

    @Override
    public Optional<Todo> getTodoById(Long id) {
        return todoRepo.findById(id);
    }

    @Override
    public Todo createTodo(Todo todo, Long categoryId) {

        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new RuntimeException(("Category not found")));
        todo.setCategory(category);
        return todoRepo.save(todo);
    }

    @Override
    public Todo updateTodo(Long id, Todo updatedTodo, Long categoryId) {

        Todo existingTodo = todoRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Todo not found"));

        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(()-> new RuntimeException("Category not found"));

        existingTodo.setDescription(updatedTodo.getDescription());
        existingTodo.setIsCompleted((updatedTodo.isCompleted()));
        existingTodo.setCategory(category);

        return todoRepo.save(existingTodo);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepo.deleteById(id);
    }
}
