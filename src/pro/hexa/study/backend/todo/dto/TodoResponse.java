package pro.hexa.study.backend.todo.dto;

import java.util.List;
import java.util.stream.Collectors;

import pro.hexa.study.backend.todo.domain.ParentTodo;
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
    public TodoResponse(List<ParentTodo> entities){
        List<TodoInquiryDto> todoList= changeIntoTodoInquiryDto(entities);
        this.todoList = todoList;


        int count_completed = 0;
        for(int i= 0; i < todoList.size(); i++){
            if(todoList.get(i).isCompleteYn()){
                count_completed += 1;
            }
        }
        this.completedCount = count_completed;
        int count_total = 0;
        for(int i = 0; i < todoList.size(); i++){
            for(int j = 0; j < todoList.get(i).getDetailContents().size(); j++){
                count_total += 1;
            }
        }
        this.totalCount = count_total;

    }
    public List<TodoInquiryDto> changeIntoTodoInquiryDto(List<ParentTodo> entities){
        return entities.stream().map(parentTodo -> new TodoInquiryDto(parentTodo.getIdList(),parentTodo.getTitle(),parentTodo.getContent(),parentTodo.getSubTitles(),
                parentTodo.getDetailContents(), parentTodo.getTimeToTakeInMinutes(),parentTodo.getStartAt(),
                parentTodo.isCompleteYn())).collect(Collectors.toList());
    }

}
