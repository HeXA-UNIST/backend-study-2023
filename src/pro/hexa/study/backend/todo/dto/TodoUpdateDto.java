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
}
