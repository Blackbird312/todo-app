package tp.supmti.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tp.supmti.todo.service.TodoService;

import java.util.List;

public class TodoServiceTest {
    private TodoService todoService;

    @BeforeEach
    public void setUp() {
        todoService = new TodoService();
        todoService.findAll().clear();
    }

    @Test
    public void testAddTodo() {
        Todo todo = new Todo(1, "Test Todo - Add", false);
        todoService.addTodo(todo);
        List<Todo> todos = todoService.findAll();
        Assertions.assertEquals(1, todoService.findAll().size());
        Assertions.assertEquals("Test Todo - Add", todos.getFirst().getTitle());
        Assertions.assertFalse(todos.getFirst().isCompleted());
    }

    @Test
    public void testFindAll() {
        Todo todo1 = new Todo(1, "Test Todo1 - findAll", false);
        Todo todo2 = new Todo(1, "Test Todo2 - findAll", false);
        todoService.addTodo(todo1);
        todoService.addTodo(todo2);
        List<Todo> todos = todoService.findAll();
        Assertions.assertEquals(2, todos.size());
    }

    @Test
    public void testCompleteTodo(){
        Todo todo = new Todo(1, "Test Todo - Complete", false);
        todoService.addTodo(todo);
        todoService.completeTodo(1);
        Assertions.assertTrue(todoService.findAll().getFirst().isCompleted());
    }

    @Test
    public void testCompleteTodoNotFound(){
        todoService.addTodo(new Todo(1, "Test Todo - Existing", false));
        todoService.completeTodo(22);
        Assertions.assertFalse(todoService.findAll().getFirst().isCompleted());
    }

    @Test
    public void testDeleteTodo(){
        Todo todo = new Todo(1, "Test Todo 1 - Delete", false);
        Todo todo2 = new Todo(2, "Test Todo 2 - Delete", false);
        todoService.addTodo(todo);
        todoService.addTodo(todo2);
        todoService.deleteTodo(1);
        Assertions.assertEquals(1, todoService.findAll().size());
        Assertions.assertEquals("Test Todo 2 - Delete", todoService.findAll().getFirst().getTitle());
    }


}
