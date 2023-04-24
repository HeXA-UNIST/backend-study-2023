package pro.hexa.study.backend.todo.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import pro.hexa.study.backend.todo.domain.ChildTodo;
import pro.hexa.study.backend.todo.domain.ParentTodo;
import pro.hexa.study.backend.todo.domain.Todo;
import pro.hexa.study.backend.todo.dto.SaveTodoRequest;
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
        List<ParentTodo> listOfParentTodo= this.todoEntities.stream().map(todo -> (ParentTodo)todo).collect(Collectors.toList());
        TodoResponse TR = new TodoResponse(listOfParentTodo);

        return TR;
    }

    /*
     * todoList 수정 요청 받아서 처리한 뒤 저장
     */
    public void saveTodo(SaveTodoRequest request) {
        // delete
        List<Long> deletedIds = request.getTodoDeleteIds();
        for(int i = 0; i < deletedIds.size(); i++){
            for(int j = 0; j < todoEntities.size(); j++) {
                if(todoEntities.get(j).getId() == deletedIds.get(i)){
                    todoEntities.remove(todoEntities.get(j));
                    ID_COUNT--;
            }
            }
        }





        // update
        List<TodoUpdateDto> updatedDtos = request.getTodoUpdateList();
        for(int i = 0; i < updatedDtos.size(); i++){
            boolean found = false;
            for(int j = 0; j < todoEntities.size(); j++){
                if(todoEntities.get(j).getId() == updatedDtos.get(i).getId()){
                    // 같을 경우 => 덮어쓰기
                    todoEntities.get(j).changeTitle(updatedDtos.get(i).getTitle());
                    todoEntities.get(j).changeDetail(updatedDtos.get(i).getDetail());
                    todoEntities.get(j).changeStartAt(updatedDtos.get(i).getStartAt());
                    todoEntities.get(j).changeTimeToTakeInMinutes(updatedDtos.get(i).getEndAt());
                    todoEntities.get(j).changeCompleteYn(updatedDtos.get(i).isCompleteYn());
                    found = true;
                    break;
                }
            }
            if (!found){
                todoEntities.add(new ChildTodo(updatedDtos.get(i).getTitle(),
                                          updatedDtos.get(i).getDetail(),
                                          updatedDtos.get(i).getTakeToTimeInMinutes(),
                                          updatedDtos.get(i).getStartAt(),
                                          updatedDtos.get(i).isCompleteYn(),
                                    (ParentTodo) todoEntities.get(todoEntities.size() - 1))
                );

            }
        }
    }
}
