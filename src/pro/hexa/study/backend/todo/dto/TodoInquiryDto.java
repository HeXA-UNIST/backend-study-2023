package pro.hexa.study.backend.todo.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TodoInquiryDto {
    private List<Long> id;
    private String title;
    private String content;
    private List<String> subTitles;
    private List<String> detailContents;
    private int timeToTakeInMinutes;
    private LocalDateTime startAt;
    private boolean completeYn;

    public TodoInquiryDto(List<Long> id, String title, String content, List<String> subTitles, List<String> detailContents, int timeToTakeInMinutes, LocalDateTime startAt, boolean completeYn) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.subTitles = subTitles;
        this.detailContents = detailContents;
        this.timeToTakeInMinutes = timeToTakeInMinutes;
        this.startAt = startAt;
        this.completeYn = completeYn;
    }

    // getters and setters
}
