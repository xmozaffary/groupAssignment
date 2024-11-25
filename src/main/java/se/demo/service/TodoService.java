package se.demo.service;

import se.demo.dao.TodoDAO;
import se.demo.entity.Todo;

import java.util.List;
import java.util.Optional;

public class TodoService {
    private final TodoDAO todoDAO;

    public TodoService(TodoDAO todoDAO){
        this.todoDAO = todoDAO;
    }

    public void addTodo(Todo todo){
        todoDAO.save(todo);
    }

    public List<Todo> getAllTodo(){
        return todoDAO.findAll();
    }
    public Optional<Todo> getTodoById(Long id){
        return todoDAO.findById(id);
    }

    public void updateTodo(Todo todo) {
        todoDAO.update(todo);
    }

    public void deleteTodo(Todo todo){
        todoDAO.delete(todo);
    }
}
