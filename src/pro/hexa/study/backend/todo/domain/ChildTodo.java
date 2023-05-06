package pro.hexa.study.backend.todo.domain;

import java.time.LocalDateTime;

public class ChildTodo extends Todo {

    private ParentTodo parent;

    public ChildTodo(String title, String detail, Short timeToTakeInMinutes, LocalDateTime startAt, boolean completeYn, ParentTodo parent) {
        super(title, detail, timeToTakeInMinutes, startAt, completeYn);
        this.setParentTodo(parent);
    }

    public ParentTodo getParent() {
        return this.parent;
    }

    public void setParentTodo(ParentTodo parent) {
        // todo: parentTodo를 지정하고, parentTodo의 details에 이 childTodo가 없으면 details에도 추가.
        this.parent = parent;
        if (!parent.getDetails().contains(this)) {
            parent.addDetailTodo(this);
        }

    }
    void completeTodo() {

        this.completeYn = true;


    }
}
