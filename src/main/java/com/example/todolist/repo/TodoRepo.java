package com.example.todolist.repo;

import com.example.todolist.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long> {
    @Query("SELECT t FROM Todo t WHERE t.reminderDate = :reminderDate AND t.isCompleted = false")
    List<Todo> findByReminderDate(LocalDate reminderDate);
}
