package knu.ainterview.controller;

import knu.ainterview.controller.dto.MemberResponseDto;
import knu.ainterview.controller.dto.ResumeDto;
import knu.ainterview.service.MemberService;
import knu.ainterview.service.ResumeService;
import knu.ainterview.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;
    private final ResumeService resumeService;


    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> findMemberInfoById() {
        return ResponseEntity.ok(memberService.findMemberInfoById(SecurityUtil.getCurrentMemberId()));
    }

    @GetMapping("/{email}")
    public ResponseEntity<MemberResponseDto> findMemberInfoByEmail(@PathVariable String email) {
        return ResponseEntity.ok(memberService.findMemberInfoByEmail(email));
    }

//    @GetMapping("/resume")
//    public ResponseEntity<List<ResumeDto>> findResumesByMemberId() {
//        return ResponseEntity.ok(resumeService.findAllByMemberId(SecurityUtil.getCurrentMemberId()));
//    }

    @GetMapping("/resume") // 둘중 더 나은걸로 선택
    public ResponseEntity<List<String>> findResumeTitlesByMemberId() {
        // todo : title -> resumeId,title 객체 만들어서 반환
        return ResponseEntity.ok(resumeService.findAllTitlesByMemberId(SecurityUtil.getCurrentMemberId()));
    }

    @GetMapping("/resume/{resumeId}")
    public ResponseEntity<ResumeDto> findResumeDetailById(@PathVariable Long resumeId) {
        return ResponseEntity.ok(resumeService.findById(resumeId));
    }

    @PutMapping("/resume/{resumeId}")
    public ResponseEntity<ResumeDto> updateResume(@PathVariable Long resumeId, @RequestBody ResumeDto newResume) {
        return ResponseEntity.ok(resumeService.update(resumeId, newResume));
    }

    @DeleteMapping("/resume/{resumeId}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long resumeId) {
        resumeService.delete(resumeId);
        return ResponseEntity.noContent().build();
    }
}