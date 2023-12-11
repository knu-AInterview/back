package knu.ainterview.entity;

import knu.ainterview.util.StringListConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resumeId;

    private String title;

    @Convert(converter = StringListConverter.class)
    private List<String> career = new ArrayList<>();

    @Convert(converter = StringListConverter.class)
    private List<String> award = new ArrayList<>();

    private String language;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private Member member;

    @Column(name = "member_id")
    private Long memberId;

    public void update(Long resumeId, String title, List<String> career, List<String> award, String language, String introduction) {
        this.resumeId = resumeId;
        this.title = title;
        this.career = career;
        this.award = award;
        this.language = language;
        this.introduction = introduction;
    }
}
