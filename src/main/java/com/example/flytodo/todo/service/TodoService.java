package com.example.flytodo.todo.service;

import com.example.flytodo.todo.domain.Todos;
import com.example.flytodo.todo.dto.UpdateTodoRequestDto;
import com.example.flytodo.todo.dto.CreateTodoRequestDto;
import com.example.flytodo.todo.dto.TodoResponseDto;
import com.example.flytodo.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    //날짜 범위 계산 및 해당 날짜의 투두 시간순으로 조회
    @Transactional(readOnly = true)
    public List<TodoResponseDto> findBydate(LocalDate date){

        //due_at이 Localdatetime이기 때문에 날짜 계산은 시작과 종료 시간을 계산하여야한다.
        //만약 10/22일을 선택 -> 26.10.22:00이 시작, 26.10.23:00이 end기간으로 설정됨
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();

        return todoRepository.findAllByDueAtGreaterThanEqualAndDueAtLessThanOrderByDueAtAsc(start, end)
                .stream()
                .map(TodoResponseDto::from)
                .toList();
    }
    //ID로 할 일 찾고 응답 DTO로 변환
    @Transactional(readOnly = true)
    public TodoResponseDto findById(Long id){
        Todos todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("할 일을 찾을 수 없습니다."));

        return TodoResponseDto.from(todo);


    }
    //등록 DTO를 Entity로 변환하고 DB에 저장
    @Transactional
    public TodoResponseDto createDto(CreateTodoRequestDto dto){
        Todos todos = Todos.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .dueAt(dto.getDue_at())
                .build();

        Todos savedTodo = todoRepository.save(todos);

        return TodoResponseDto.from(savedTodo);
    }

    //기존 할 일을 찾아 내용 수정 및 저장
    @Transactional
    public TodoResponseDto updateDto(Long id, UpdateTodoRequestDto dto){

        //1. 기존 할 일 조회
        Todos updatedTodos = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("할 일을 찾을 수 없습니다."));

        //2. 수정된 내용으로 업데이트
        updatedTodos.updateTodo(dto.getUpdatedTitle(), dto.getUpdatedDescription(), dto.getUpdatedDate(), dto.getUpdatedDue_at());

        return TodoResponseDto.from(updatedTodos);
    }

    //완료 반영
    @Transactional
    public TodoResponseDto completeTodo(Long id, boolean completed){
        Todos completeTodos = todoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("할 일을 찾을 수 없습니다."));

        completeTodos.updateCompleted(completed);

        return TodoResponseDto.from(completeTodos);
    }


    //기존 할 일을 찾아 삭제
    @Transactional
    public void deleteTodo(Long id) {
        Todos deleteTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("할 일을 찾을 수 없습니다."));

        todoRepository.delete(deleteTodo);
    }
}

