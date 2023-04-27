package pro.hexa.study.backend.todo.domain;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import pro.hexa.study.backend.todo.service.TodoService;

public class ParentTodo extends Todo {

    private List<ChildTodo> details;

    public ParentTodo(String title, String content, Short timeToTakeInMinutes, LocalDateTime startAt, boolean completeYn) {
        super(title, content, timeToTakeInMinutes, startAt, completeYn);
        this.details = Collections.emptyList();
    }

    public List<ChildTodo> getDetails() {
        return this.details;
    }

    public void addDetailTodo(ChildTodo detail) {
        //  details에 detail을 추가하고, detail의 parent로 지금 이 parentTodo를 지정하기(parent가 이 객체가 아닐때만).
        //  parentTodo의 걸리는 시간은 details의 걸리는 시간의 합과 같도록 항상 유지해줘야한다.
        detail.setParentTodo(this);
        details.add(detail);
        this.timeToTakeInMinutes = (short) (this.timeToTakeInMinutes + detail.timeToTakeInMinutes);
    }

    public void deleteDetailTodo(ChildTodo detail) {
        // detail을 details에서 삭제하고, detail의 parent에서도 지금 이 parentTodo를 삭제하기.
        // parentTodo의 걸리는 시간은 details의 걸리는 시간의 합과 같도록 항상 유지해줘야한다.

        int index = getDetailsIndexbyChildTodo(detail);

        if (index == -1) {
            return;
        }

        this.timeToTakeInMinutes = (short) (this.timeToTakeInMinutes - details.get(index).timeToTakeInMinutes);
        details.remove(index);
    }

    public void completeTodo(ChildTodo childTodo) {
        // 입력 받은 childTodo의 완료 처리와 동시에 parentTodo의 모든 details 내에 있는 todo가 완료 되었으면 parentTodo도 완료 처리.
        int index=getDetailsIndexbyChildTodo(childTodo);

        details.get(index).completeTodo();

        boolean isAllCompleted=details.stream()
            .allMatch(detail -> detail.completeYn);
        if(isAllCompleted){
            this.completeYn=true;
        }

    }

    public void updateTodo(String title, String content, Short timeToTakeInMinutes, LocalDateTime startAt, boolean completeYn){
        this.id = TodoService.ID_COUNT++;
        this.title = title;
        this.content = content;
        this.timeToTakeInMinutes = timeToTakeInMinutes;
        this.startAt = startAt;
        this.completeYn = completeYn;
    }

    public int getDetailsIndexbyChildTodo(ChildTodo detail){
        int index = IntStream.range(0, details.size())
            .filter(i -> Objects.equals(details.get(i), detail))
            .findFirst()
            .orElse(-1);
        return index;
    }
}
