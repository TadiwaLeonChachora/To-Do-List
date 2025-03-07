package com.example.todolist.services;

import com.example.todolist.models.Todo;
import com.example.todolist.repositories.CategoryRepo;

import java.util.List;
import java.util.Optional;

public interface TodoService {

    public List<Todo> getAllTodos();

    public Optional<Todo> getTodoById(Long id);

    public Todo createTodo(Todo todo, Long categoryId);

    public Todo updateTodo(Long id, Todo updatedTodo, Long categoryId);

    public void deleteTodo(Long id);
}
