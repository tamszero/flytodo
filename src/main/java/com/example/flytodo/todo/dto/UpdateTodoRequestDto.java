package com.example.flytodo.todo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@ToString
@NoArgsConstructor
public class UpdateTodoRequestDto {

    @NotBlank(message = "할 일 변경")
    private String updatedTitle;

    private String updatedDescription;

    private LocalDate updatedDate;

    private LocalDateTime updatedDue_at;
}
