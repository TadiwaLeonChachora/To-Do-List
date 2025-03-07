package com.example.todolist.controllers;

import com.example.todolist.models.Todo;
import com.example.todolist.repositories.TodoRepo;
import com.example.todolist.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    // endpoints

    // Get all To do items
    @GetMapping
    public List<Todo> getAllTodos(){
        return todoService.getAllTodos();
    }


    // get a specific to do item by id
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id){
        return todoService.getTodoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // create a new to do item
    @PostMapping
    public Todo createTodo(@RequestBody Todo todo, @RequestParam Long categoryId){
        return todoService.createTodo(todo, categoryId);
    }
    //upadte an exisiting to do item by id
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo, @RequestParam Long categoryId){
        return ResponseEntity.ok(todoService.updateTodo(id, updatedTodo, categoryId));
    }
    // delete a to do item by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

}
