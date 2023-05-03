package pro.hexa.study.backend.todo.dto;

import java.time.LocalDateTime;
import java.util.List;

/*
 * todoList 조회용 dto
 */
public class TodoInquiryDto {


    private List<Long> id; // 맨 첫 id가 parent, 그 뒤로 따라오는건 child
    private String title; // 제목
    private String content; // 상세 내용
    private List<String> subTitles; // child title list
    private List<String> detailContents; // child content list
    private Short remainingTime; // 예상되는 남은 시간(분)
    private LocalDateTime startAt; // 시작 예정시간
    private boolean completeYn; // 완료 여부

    public TodoInquiryDto(List<Long> id, String title, String content, List<String> subTitles, List<String> detailContents,
        Short remainingTime, LocalDateTime startAt, boolean completeYn) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.subTitles = subTitles;
        this.detailContents = detailContents;
        this.remainingTime = remainingTime;
        this.startAt = startAt;
        this.completeYn = completeYn;
    }

    public boolean getCompleteYn() {
        return this.completeYn;
    }
}
