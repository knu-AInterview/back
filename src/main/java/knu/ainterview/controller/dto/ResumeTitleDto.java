package knu.ainterview.controller.dto;

import lombok.Getter;

@Getter
public class ResumeTitleDto {
    private Long resumeId;
    private String title;

    public ResumeTitleDto(Long resumeId, String title) {
        this.resumeId = resumeId;
        this.title = title;
    }
}
