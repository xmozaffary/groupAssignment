package se.demo.dao;

import se.demo.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoDAO {
    void save(Todo todo);
    Optional<Todo> findById(Long id);
    List<Todo> findAll();
    void update (Todo todo);
    void delete (Todo todo);
}