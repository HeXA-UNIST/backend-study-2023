package pro.hexa.study.backend.todo.service;

import java.util.Collections;
import java.util.List;
import pro.hexa.study.backend.todo.domain.Todo;
import pro.hexa.study.backend.todo.dto.SaveTodoRequest;
import pro.hexa.study.backend.todo.dto.TodoResponse;

/*
 * todoList를 위한 변환 로직
 */
public class TodoService {

    public static Long ID_COUNT;
    private List<Todo> todoEntities; // 저장되어있는 todo객체 전체

    /*
     * don't touch this function.
     */
    public TodoService() {
        ID_COUNT = 0L;
        this.todoEntities = Collections.emptyList();
    }

    /*
     * don't touch this function.
     */
    public void saveTodoEntities(List<Todo> todoEntities) {
        this.todoEntities = todoEntities;
    }

    /*
     * todoList를 todoResponse로 변환
     */
    public TodoResponse getTodoResponse() {
        // todo
        return null;
    }

    /*
     * todoList 수정 요청 받아서 처리한 뒤 저장
     */
    public void saveTodo(SaveTodoRequest request) {
        // todo
    }
}
