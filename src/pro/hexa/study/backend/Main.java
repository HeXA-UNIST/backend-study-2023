package pro.hexa.study.backend;

import java.util.List;
import pro.hexa.study.backend.todo.domain.Todo;
import pro.hexa.study.backend.todo.dto.SaveTodoRequest;
import pro.hexa.study.backend.todo.dto.TodoResponse;
import pro.hexa.study.backend.todo.service.TodoService;
import pro.hexa.study.backend.todo.service.TodoSetupService;

public class Main {

    private final TodoService todoService;
    private final TodoSetupService todoSetupService;

    Main() {
        this.todoService = new TodoService();
        this.todoSetupService = new TodoSetupService();
    }

    public static void main() {
        Main main = new Main();
        main.setup();
        main.service();
    }

    private void setup() {
        // TODO데이터 setup
        List<Todo> todoEntities = todoSetupService.setup();
        todoService.saveTodoEntities(todoEntities);
    }

    private void service() {
        // Todo데이터 조회
        TodoResponse todoResponse = todoService.getTodoResponse();
        printTodo(todoResponse);

        // Todo데이터 수정
        SaveTodoRequest request = new SaveTodoRequest();
        todoService.saveTodo(request);

        // Todo데이터 조회
        todoResponse = todoService.getTodoResponse();
        printTodo(todoResponse);
    }

    private void printTodo(TodoResponse todoResponse) {
        System.out.println(todoResponse);
    }
}
