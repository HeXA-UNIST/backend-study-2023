package pro.hexa.study.backend.todo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pro.hexa.study.backend.todo.domain.Todo;
import pro.hexa.study.backend.todo.domain.ParentTodo;
import pro.hexa.study.backend.todo.dto.SaveTodoRequest;
import pro.hexa.study.backend.todo.dto.TodoInquiryDto;
import pro.hexa.study.backend.todo.dto.TodoResponse;
import pro.hexa.study.backend.todo.dto.TodoUpdateDto;

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
        List<TodoInquiryDto> todoInquiryDtoList = new ArrayList<>();
        for (Todo todo : todoEntities) {
            if (todo instanceof ParentTodo) {
                ParentTodo parentTodo = (ParentTodo) todo;

                List<Long> idList = new ArrayList<>();
                idList.add(parentTodo.getId());

                List<String> subTitleList = new ArrayList<>();
                List<String> detailContentList = new ArrayList<>();

                for (Todo childTodo : todoEntities) {
                    if (childTodo instanceof ChildTodo) {
                        ChildTodo child = (ChildTodo) childTodo;
                        if (child.getParentTodo().equals(parentTodo)) {
                            idList.add(child.getId());
                            subTitleList.add(child.getTitle());
                            detailContentList.add(child.getDetail());
                        }
                    }
                }

                TodoInquiryDto todoInquiryDto = new TodoInquiryDto();
                todoInquiryDto.setId(idList);
                todoInquiryDto.setTitle(parentTodo.getTitle());
                todoInquiryDto.setContent(parentTodo.getDetail());
                todoInquiryDto.setSubTitles(subTitleList);
                todoInquiryDto.setDetailContents(detailContentList);
                todoInquiryDto.setRemainingTime(parentTodo.getRemainingTime());
                todoInquiryDto.setStartAt(parentTodo.getStartAt());
                todoInquiryDto.setCompleteYn(parentTodo.isCompleteYn());

                todoInquiryDtoList.add(todoInquiryDto);
            }
        }

        return todoResponse;
    }
    /*
     * todoList 수정 요청 받아서 처리한 뒤 저장
     */
    public void saveTodo(SaveTodoRequest request) {
        // todo
        List<Long> todoDeleteIds=request.getTodoDeleteIds();
        for (Long id: todoDeleteIds) {

        }

        List<TodoUpdateDto> todoUpdateList = request.getTodoUpdateList();
        for(TodoUpdateDto t: todoUpdateList) {

        }
    }
}
