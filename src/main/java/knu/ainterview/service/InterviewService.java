package knu.ainterview.service;

import knu.ainterview.controller.dto.*;
import knu.ainterview.entity.Interview;
import knu.ainterview.entity.Resume;
import knu.ainterview.repository.InterviewRepository;
import knu.ainterview.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final ResumeRepository resumeRepository;

    private static String GPT_API = "http://155.230.36.16:8700/question_ask";
    private static String TRAINING_MODEL = "http://155.230.36.16:8500/question_ask";

    @Transactional
    public QnAResponseDto registerInterview(InterviewRequestDto interviewRequestDto, Long memberId) {
        List<QnA> qnaList = getQnAListFromAIServer(interviewRequestDto);

        Resume resume = resumeRepository.findById(interviewRequestDto.getResumeDto().getResumeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid resumeId"));

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

    public List<QnA> getQnAListFromAIServer(InterviewRequestDto interviewRequestDto){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");

        HttpEntity<InterviewRequestDto> request = new HttpEntity<>(interviewRequestDto, httpHeaders);
        ResponseEntity<List<QnA>> response = restTemplate.exchange(
                GPT_API,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<List<QnA>>() {}
        );

        return response.getBody();
    }
}
