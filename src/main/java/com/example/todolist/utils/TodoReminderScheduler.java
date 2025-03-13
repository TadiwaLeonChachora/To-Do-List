package com.example.todolist.utils;

import com.example.todolist.model.Todo;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TodoReminderScheduler {

    @Autowired
    private TodoService todoService;

    @Scheduled(cron = "0 0 9 * * ?")
    public void checkReminders(){
        LocalDate today = LocalDate.now();
        List<Todo> todos = todoService.getTodoWithReminderDueToday();

        for(Todo todo: todos){
            System.out.println("Reminder: Task '" + todo.getDescription()+ "' is due on " + todo.getDueDate()+" ! ");
        }
    }
}
