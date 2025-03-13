package com.example.todolist.service;

import com.example.todolist.model.Category;
import com.example.todolist.model.Todo;
import com.example.todolist.repo.CategoryRepo;
import com.example.todolist.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        return Optional.ofNullable(todoRepo.findById(id).orElseThrow(() -> new RuntimeException("Todo not found")));
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
        existingTodo.setCompleted((updatedTodo.isCompleted()));
        existingTodo.setDueDate(updatedTodo.getDueDate());
        existingTodo.setReminderDate(updatedTodo.getReminderDate());
        existingTodo.setCategory(category);

        return todoRepo.save(existingTodo);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepo.deleteById(id);
    }

    @Override
    public List<Todo> getTodoWithReminderDueToday(){
        LocalDate today = LocalDate.now();
        return todoRepo.findByReminderDate(today);
    }
}
