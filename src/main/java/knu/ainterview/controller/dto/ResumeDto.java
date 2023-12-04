package knu.ainterview.controller.dto;

import knu.ainterview.entity.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResumeDto {

    private Long resumeId;

    private String title;

    private List<String> career;

    private List<String> award;

    private String language;

    private String introduction;


    public static ResumeDto of(Resume resume) {
        return new ResumeDto(resume.getResumeId(), resume.getTitle(), resume.getCareer(), resume.getAward(), resume.getLanguage(), resume.getIntroduction());
    }
}
