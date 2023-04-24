package pro.hexa.study.backend.todo.dto;

import java.time.LocalDateTime;

/*
 * todoList의 업데이트를 위한 dto
 */
public class TodoUpdateDto {

    private Long id; // 있으면 찾아서 업데이트하고, 없으면 새로 추가
    private String title; // 제목
    private String detail; // 상세 내용
    private LocalDateTime startAt; // 시작 예정시간
    private LocalDateTime endAt; // 완료 예정시간
    private boolean completeYn; // 완료 여부

    public Long getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getDetail(){
        return this.detail;
    }
    public LocalDateTime getStartAt(){
        return this.startAt;
    }
    public LocalDateTime getEndAt(){
        return this.endAt;
    }
    public boolean isCompleteYn(){
        return this.completeYn;
    }
    public Short getTakeToTimeInMinutes(){
        return (short)(this.endAt.getMinute()- this.startAt.getMinute());
    }
}
