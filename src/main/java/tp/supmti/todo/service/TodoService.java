package tp.supmti.todo.service;

import tp.supmti.todo.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoService {
    private final List<Todo> todos = new ArrayList<>();

    public List<Todo> findAll() {
        return todos;
    }

    public void addTodo(Todo todo) {
        todos.add(todo);
    }

    public void completeTodo( int id){
        Todo todo = todos.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        Optional.ofNullable(todo).ifPresent(t -> t.setCompleted(true));
    }

    public void deleteTodo(int id){
        todos.removeIf(t -> t.getId() == id);
    }
}
