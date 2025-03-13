package com.example.todolist.service;

import com.example.todolist.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {

    public List<Todo> getAllTodos();

    public Optional<Todo> getTodoById(Long id);

    public Todo createTodo(Todo todo, Long categoryId);

    public Todo updateTodo(Long id, Todo updatedTodo, Long categoryId);

    public void deleteTodo(Long id);

    public List<Todo> getTodoWithReminderDueToday();
}
