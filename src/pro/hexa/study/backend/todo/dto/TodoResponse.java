package pro.hexa.study.backend.todo.dto;

import java.util.List;
import pro.hexa.study.backend.todo.utils.GetUtils;

/*
 * 할 일 목록 처리 등을 위한 객체
 */
public class TodoResponse {

    private List<TodoInquiryDto> todoList;
    private Integer completedCount; // 완료한 todo객체 개수
    private Integer totalCount; // 전체 todo객체 개수

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
