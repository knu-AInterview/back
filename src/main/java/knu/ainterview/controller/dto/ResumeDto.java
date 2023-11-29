package knu.ainterview.controller.dto;

import knu.ainterview.entity.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResumeDto {

    private Long resumeId;

    private String title;

    private String job;

    private Integer career;

    private String introduction;


    public static ResumeDto of(Resume resume) {
        return new ResumeDto(resume.getResumeId(), resume.getTitle(), resume.getJob(), resume.getCareer(), resume.getIntroduction());
    }
}
