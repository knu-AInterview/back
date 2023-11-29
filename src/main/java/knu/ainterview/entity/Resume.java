package knu.ainterview.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;


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

    private String job;

    private Integer career;

    @Column(length = 500)
    private String introduction;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private Member member;

    @Column(name = "member_id")
    private Long memberId;

    public void update(String title, String job, Integer career, String introduction) {
        this.title = title;
        this.job = job;
        this.career = career;
        this.introduction = introduction;
    }
}
