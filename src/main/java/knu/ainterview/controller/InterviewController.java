package knu.ainterview.controller;

import knu.ainterview.controller.dto.InterviewRequestDto;
import knu.ainterview.controller.dto.QnAResponseDto;
import knu.ainterview.service.InterviewService;
import knu.ainterview.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interview")
public class InterviewController {

    private final InterviewService interviewService;


    @PostMapping()
    public ResponseEntity<QnAResponseDto> registerInterview(@RequestBody InterviewRequestDto interviewRequestDto) {
        return ResponseEntity.ok(interviewService.registerInterview(interviewRequestDto, SecurityUtil.getCurrentMemberId()));
    }
}
