package com.example.flytodo.todo.dto;

import com.example.flytodo.todo.domain.Todos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponseDto {

    private Long id;
    private String title;
    private LocalDate date;
    private String description;
    private LocalDateTime due_at;
    private boolean completed;

    public static TodoResponseDto from(Todos todos){
        return new TodoResponseDto(
                todos.getId(),
                todos.getTitle(),
                todos.getDate(),
                todos.getDescription(),
                todos.getDueAt(),
                todos.isCompleted()
        );
    }

}
