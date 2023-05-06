package pro.hexa.study.backend.todo.domain;

import java.time.LocalDateTime;
import pro.hexa.study.backend.todo.service.TodoService;

/*
 * 할 일 객체
 */
public abstract class Todo {

    protected Long id;
    protected String title; // 제목
    protected String content; // 상세 내용
    protected Short timeToTakeInMinutes; // 예상되는 걸리는 시간(분)
    protected LocalDateTime startAt; // 시작 예정시간
    protected boolean completeYn; // 완료 여부

    protected Todo(String title, String content, Short timeToTakeInMinutes, LocalDateTime startAt, boolean completeYn) {
        this.id = TodoService.ID_COUNT++;
        this.title = title;
        this.content = content;
        this.timeToTakeInMinutes = timeToTakeInMinutes;
        this.startAt = startAt;
        this.completeYn = completeYn;
    }

    public Long getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getContent(){
        return this.content;
    }
    public Short getTimeToTakeInMinutes(){
        return this.timeToTakeInMinutes;
    }
    public LocalDateTime getStartAt(){
        return this.startAt;
    }
    public boolean getCompleteYn(){
        return this.completeYn;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimeToTakeInMinutes(Short timeToTakeInMinutes) {
        this.timeToTakeInMinutes = timeToTakeInMinutes;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public void setCompleteYn(boolean completeYn) {
        this.completeYn = completeYn;
    }
}



