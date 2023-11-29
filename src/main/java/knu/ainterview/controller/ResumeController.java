package knu.ainterview.controller;

import knu.ainterview.controller.dto.ResumeDto;
import knu.ainterview.service.ResumeService;
import knu.ainterview.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resume")
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping()
    public ResponseEntity<ResumeDto> registerResume(@RequestBody ResumeDto resumeDto) {
        return ResponseEntity.ok(resumeService.registerResume(resumeDto, SecurityUtil.getCurrentMemberId()));
    }

}
