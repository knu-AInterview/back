package knu.ainterview.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InterviewRequestDto {

    private String title;
    private String job;
    private String requirement;
    private ResumeDto resumeDto;

}
