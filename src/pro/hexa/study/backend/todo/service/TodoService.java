package pro.hexa.study.backend.todo.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import pro.hexa.study.backend.todo.domain.ChildTodo;
import pro.hexa.study.backend.todo.domain.ParentTodo;
import pro.hexa.study.backend.todo.domain.Todo;
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
        //
        List<TodoInquiryDto> todoList=Collections.emptyList();
        for(Todo todo:this.todoEntities){
            Collections.emptyList().add(createTodoInquiryDtoFromParentTodo((ParentTodo) todo));
        }

        return new TodoResponse(todoList);
    }

    private TodoInquiryDto createTodoInquiryDtoFromParentTodo(ParentTodo parentTodo) {
        List<Long> id = Collections.emptyList();
        List<String> subTitles = Collections.emptyList();
        List<String> detailContents = Collections.emptyList();
        id.add(parentTodo.getId());
        String title = parentTodo.getTitle();
        String content = parentTodo.getContent();
        for (ChildTodo childTodo : parentTodo.getDetails()) {
            id.add(childTodo.getId());
            subTitles.add(childTodo.getTitle());
            detailContents.add(childTodo.getContent());
        }
        short remainingTime=parentTodo.getTimeToTakeInMinutes();
        LocalDateTime startAt=parentTodo.getStartAt();
        boolean completeYn= parentTodo.getCompleteYn();
        return new TodoInquiryDto(id, title,content,subTitles,detailContents,remainingTime,startAt,completeYn);
    }

    /*
     * todoList 수정 요청 받아서 처리한 뒤 저장
     */
    public void saveTodo(SaveTodoRequest request) {
        // done
        for (TodoUpdateDto todoUpdateDto : request.getTodoUpdateList()) {
            boolean isTodoInTheList = this.todoEntities.stream()
                .anyMatch(todo -> todo.getId() == todoUpdateDto.getId());
            if (isTodoInTheList) {
                int index = IntStream.range(0, todoEntities.size())
                    .filter(i -> todoEntities.get(i).getId() == todoUpdateDto.getId())
                    .findFirst()
                    .orElse(-1);
                ((ParentTodo) todoEntities.get(index)).updateTodo(todoUpdateDto.getTitle(),
                    todoUpdateDto.getDetail(),
                    (short) ChronoUnit.MINUTES.between(todoUpdateDto.getEndAt(), todoUpdateDto.getStartAt()),
                    todoUpdateDto.getStartAt(),
                    todoUpdateDto.getCompleteYn());
            } else {
                ParentTodo newParentTodo = new ParentTodo(todoUpdateDto.getTitle(),
                    todoUpdateDto.getDetail(),
                    (short) ChronoUnit.MINUTES.between(todoUpdateDto.getEndAt(), todoUpdateDto.getStartAt()),
                    todoUpdateDto.getStartAt(),
                    todoUpdateDto.getCompleteYn());
                this.todoEntities.add(newParentTodo);
            }
        }
        for (Long todoDeleteId : request.getTodoDeleteIds()) {
            IntStream.range(0,todoEntities.size()).forEach(i -> {
                if(todoEntities.get(i).getId()==todoDeleteId){
                    todoEntities.remove(i);
                    return;
                }
            });
        }
    }
}
