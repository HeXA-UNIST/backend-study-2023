package pro.hexa.study.backend.todo.dto;

import java.util.List;
import pro.hexa.study.backend.todo.utils.GetUtils;

/*
 * 할 일 목록 처리 등을 위한 객체
 */
public class TodoResponse {
    private List<TodoInquiryDto> todoList;
    private int completedCount;
    private int totalCount;

    // Setter 메서드
    public void setTodoList(List<TodoInquiryDto> todoList) {
        this.todoList = todoList;
    }

    public void setCompletedCount(int completedCount) {
        this.completedCount = completedCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<TodoInquiryDto> getTodoList() {
        return this.todoList;
    }

    public int getCompletedCount() {
        return GetUtils.getIntegerAsInt(this.completedCount);
    }

    public int getTotalCount() {
        return GetUtils.getIntegerAsInt(this.totalCount);
    }
}
