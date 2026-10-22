package com.example.flytodo.todo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "todos")
@Builder
public class Todos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 80)
    private String title;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "due_at")
    private LocalDateTime dueAt;

    @Column(name = "completed")
    private boolean completed = false;

    @CreationTimestamp
    private LocalDateTime completed_at;
    @CreationTimestamp
    private LocalDateTime created_at;
    @CreationTimestamp
    private LocalDateTime updated_at;

    // 투두 업데이트
    public void updateTodo(String title, String description, LocalDate date, LocalDateTime due_at){
        this.title = title;
        this.description = description;
        this.date = date;
        this.dueAt = due_at;
    }

    // 완료 업데이트
    public void updateCompleted(boolean completed){
        this.completed = completed;
        this.completed_at = completed ? LocalDateTime.now() : null;
    }

}
