package knu.ainterview.controller.dto;

import knu.ainterview.entity.Interview;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class InterviewResponseDto {

    private Long interviewId;

    private String title;

    private String job;

    private String requirement;

    private List<QnA> qnaList;

    private ResumeDto resumeDto;

    public static InterviewResponseDto of(Interview interview) {
        ResumeDto resumeDto = ResumeDto.of(interview.getResume());
        return new InterviewResponseDto(interview.getInterviewId(), interview.getTitle(), interview.getJob(), interview.getRequirement(), interview.getQnaList(), resumeDto);
    }
}
