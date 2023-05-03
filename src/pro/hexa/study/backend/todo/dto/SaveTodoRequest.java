package pro.hexa.study.backend.todo.dto;

import java.util.List;

/*
 * todoList 수정 요청
 */
public class SaveTodoRequest {

    private List<Long> todoDeleteIds;
    private List<TodoUpdateDto> todoUpdateList;

    public List<Long> getTodoDeleteIds() {
        return this.todoDeleteIds;
    }

    public List<TodoUpdateDto> getTodoUpdateList() {
        return this.todoUpdateList;
    }

}
