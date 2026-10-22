package com.example.flytodo.todo.dto;

//투두리스트 등록

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@ToString
@NoArgsConstructor
public class CreateTodoRequestDto {

    @NotNull(message = "날 짜 선택")
    private LocalDate date;

    @NotBlank(message = "할 일 입력")
    private String title;

    private String description;

    @NotNull
    private LocalDateTime due_at;
}
