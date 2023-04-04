package pro.hexa.study.backend.todo.service;

import java.time.LocalDateTime;
import java.util.List;
import pro.hexa.study.backend.todo.domain.ChildTodo;
import pro.hexa.study.backend.todo.domain.ParentTodo;
import pro.hexa.study.backend.todo.domain.Todo;

/*
 * todoList 데이터 초기 설정을 위한 서비스
 */
public class TodoSetupService {

    public List<Todo> setup() {
        LocalDateTime projectStartAt = LocalDateTime.of(2023, 4, 11, 23, 0, 0);
        ParentTodo parentTodo = new ParentTodo(
            "해야할 일", "기존에 있는 코드를 수정하지 않고, Main.main 함수가 의도하는 대로 잘 돌아가도록 하기.",
            (short) 60, projectStartAt, false
        );

        Todo childTodo1 = new ChildTodo(
            "첫번째 세부사항", "ParentTodo와 ChildTodo 내에 todo 작업 완료하기.",
            (short) 20, projectStartAt, false, parentTodo
        );
        Todo childTodo2 = new ChildTodo(
            "두번째 세부사항", "TodoService에 있는 todo 작업 완료하기.",
            (short) 40, projectStartAt, false, parentTodo
        );

        return List.of(
            parentTodo,
            childTodo1,
            childTodo2
        );
    }
}
