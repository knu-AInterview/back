package knu.ainterview.service;

import knu.ainterview.controller.dto.ResumeDto;
import knu.ainterview.entity.Resume;
import knu.ainterview.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ResumeService {
    private final ResumeRepository resumeRepository;

    public ResumeDto registerResume(ResumeDto resumeDto, Long memberId) {
        Resume resume = Resume.builder()
                .title(resumeDto.getTitle())
                .job(resumeDto.getJob())
                .career(resumeDto.getCareer())
                .introduction(resumeDto.getIntroduction())
                .memberId(memberId)
                .build();

        return ResumeDto.of(resumeRepository.save(resume));
    }

    public List<ResumeDto> findAllByMemberId(Long memberId) {
        return resumeRepository.findAllByMemberId(memberId)
                .stream().map(ResumeDto::of)
                .collect(Collectors.toList());
    }

    public List<String> findAllTitlesByMemberId(Long memberId) {
        return resumeRepository.findAllByMemberId(memberId)
                .stream()
                .map(Resume::getTitle)
                .collect(Collectors.toList());
    }

    public ResumeDto findById(Long resumeId) {
        return resumeRepository.findById(resumeId)
                .map(ResumeDto::of)
                .orElseThrow(() -> new IllegalArgumentException("해당 이력서가 존재하지 않습니다."));
    }

    public ResumeDto update(Long resumeId, ResumeDto newResume) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 이력서가 존재하지 않습니다."));
        resume.update(newResume.getTitle(), newResume.getJob(), newResume.getCareer(), newResume.getIntroduction());
        return ResumeDto.of(resumeRepository.save(resume));
    }

    public void delete(Long resumeId) {
        if (!resumeRepository.existsById(resumeId)) {
            throw new IllegalArgumentException("해당 이력서가 존재하지 않습니다.");
        }
        resumeRepository.deleteById(resumeId);
    }

}
