package knu.ainterview.controller.dto;

import knu.ainterview.entity.Interview;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QnAResponseDto {

    private Long interviewId;
    private List<QnA> qnaList;

    public static QnAResponseDto of(Interview interview) {
        return new QnAResponseDto(interview.getInterviewId(), interview.getQnaList());
    }


}
