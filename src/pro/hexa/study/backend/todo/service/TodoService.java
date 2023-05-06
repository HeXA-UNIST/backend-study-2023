package pro.hexa.study.backend.todo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        int completedCount = 0;
        int totalCount = 0;

        for (Todo todo : todoEntities) {
            if (todo instanceof ParentTodo) {
                ParentTodo parentTodo = (ParentTodo) todo;
                List<String> subTitles = parentTodo.getDetails().stream().map(Todo::getTitle).collect(Collectors.toList());
                List<String> detailContents = parentTodo.getDetails().stream().map(Todo::getContent).collect(Collectors.toList());
                List<Long> ids = Arrays.asList(parentTodo.getId());
                TodoInquiryDto todoInquiryDto = new TodoInquiryDto(
                        ids,
                        parentTodo.getTitle(),
                        parentTodo.getContent(),
                        subTitles,
                        detailContents,
                        parentTodo.getTimeToTakeInMinutes(),
                        parentTodo.getStartAt(),
                        parentTodo.getCompleteYn()
                );
                todoInquiryDtoList.add(todoInquiryDto);
                totalCount++;
                if (parentTodo.getCompleteYn()) {
                    completedCount++;
                }
            }
        }

        TodoResponse todoResponse = new TodoResponse();
        todoResponse.setTodoList(todoInquiryDtoList);
        todoResponse.setCompletedCount(completedCount);
        todoResponse.setTotalCount(totalCount);

        return todoResponse;
    }

    /*
     * todoList 수정 요청 받아서 처리한 뒤 저장
     */
    public void saveTodo(SaveTodoRequest request) {
        // Delete todos
        for (Long deleteId : request.getTodoDeleteIds()) {
            todoEntities.removeIf(todo -> todo.getId().equals(deleteId));
        }

        // Update or add todos
        for (TodoUpdateDto todoUpdateDto : request.getTodoUpdateList()) {
            Todo todo = findTodoById(todoUpdateDto.getId());
            if (todo != null) {
                // Update todo
                todo.setTitle(todoUpdateDto.getTitle());
                todo.setContent(todoUpdateDto.getDetail());
                todo.setStartAt(todoUpdateDto.getStartAt());
                todo.setCompleteYn(todoUpdateDto.getCompleteYn());
            } else {
                // Add new todo
                ParentTodo parentTodo = new ParentTodo(
                        todoUpdateDto.getTitle(),
                        todoUpdateDto.getDetail(),
                        (short) 0,
                        todoUpdateDto.getStartAt(),
                        todoUpdateDto.getCompleteYn()
                );
                todoEntities.add(parentTodo);
            }
        }
    }

    private Todo findTodoById(Long id) {
        if (id == null) {
            return null;
        }
        for (Todo todo : todoEntities) {
            if (todo.getId().equals(id)) {
                return todo;
            }
        }
        return null;
    }

    // ... 기존 코드 ...
}