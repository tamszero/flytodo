package com.example.flytodo.todo.repository;

import com.example.flytodo.todo.domain.Todos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todos, Long> {

    //할일 등록
    //int insert(Todos todos); -> JPA가 이미 제공
    //id별 조회
    //Optional<Todos> findById(Long id); -> 도 이미 JPA가 제공 -> 서비스에만 작성하면 됨

    //날짜별 조회
    List<Todos> findAllByDate(LocalDate date);

    //시간순(오름차순) 정렬
    List<Todos> findAllByDueAtGreaterThanEqualAndDueAtLessThanOrderByDueAtAsc(LocalDateTime start, LocalDateTime end);
}
