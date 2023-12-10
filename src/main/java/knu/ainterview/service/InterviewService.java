package knu.ainterview.service;

import knu.ainterview.controller.dto.*;
import knu.ainterview.entity.Interview;
import knu.ainterview.entity.Resume;
import knu.ainterview.repository.InterviewRepository;
import knu.ainterview.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final ResumeRepository resumeRepository;

    @Transactional
    public QnAResponseDto registerInterview(InterviewRequestDto interviewRequestDto, Long memberId) {

        //todo 리스트 대신 서버 통신

        Resume resume = resumeRepository.findById(interviewRequestDto.getResumeDto().getResumeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid resumeId"));

        List<QnA> qnaList = Arrays.asList(
                new QnA("1", "6"),
                new QnA("2", "7"),
                new QnA("3", "8"),
                new QnA("4", "9"),
                new QnA("5", "10")
        );
        Interview interview = Interview.builder()
                .title(interviewRequestDto.getTitle())
                .job(interviewRequestDto.getJob())
                .requirement(interviewRequestDto.getRequirement())
                .qnaList(qnaList)
                .resume(resume)
                .memberId(memberId)
                .build();

        return QnAResponseDto.of(interviewRepository.save(interview));
    }

    public List<InterviewTitleDto> findAllTitlesByMemberId(Long memberId) {
        return interviewRepository.findAllByMemberId(memberId)
                .stream()
                .map(interview -> new InterviewTitleDto(interview.getInterviewId(), interview.getTitle()))
                .collect(Collectors.toList());
    }

    public InterviewResponseDto findById(Long interviewId) {
        return interviewRepository.findById(interviewId)
                .map(InterviewResponseDto::of)
                .orElseThrow(() -> new IllegalArgumentException("해당 인터뷰가 존재하지 않습니다."));
    }
}
