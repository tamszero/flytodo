package com.example.flytodo.todo.controller;

import com.example.flytodo.todo.dto.CreateTodoRequestDto;
import com.example.flytodo.todo.dto.TodoResponseDto;
import com.example.flytodo.todo.dto.UpdateTodoRequestDto;
import com.example.flytodo.todo.service.TodoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    //날짜 별 투두 조회
    @GetMapping
    public List<TodoResponseDto> getTodosByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date) {
        return todoService.findBydate(date);
    }

    //id로 투두 상세 조회
    @GetMapping("/{id}")
    public TodoResponseDto getTodoDetail(@PathVariable Long id){
        return todoService.findById(id);

    }

    //투두 등록
    @PostMapping
    public TodoResponseDto createTodo(@RequestBody CreateTodoRequestDto dto){
        return todoService.createDto(dto);

    }

    //투두 수정
    @PatchMapping("/{id}")
    public TodoResponseDto updateTodo(@PathVariable Long id, @RequestBody UpdateTodoRequestDto dto){
        return todoService.updateDto(id, dto);
    }


    //투두 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);

        return ResponseEntity.noContent().build();
    }


}
