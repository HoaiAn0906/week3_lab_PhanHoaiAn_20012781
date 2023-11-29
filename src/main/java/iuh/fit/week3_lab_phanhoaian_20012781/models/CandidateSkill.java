package iuh.fit.week3_lab_phanhoaian_20012781.models;

import iuh.fit.week3_lab_phanhoaian_20012781.enums.SkillLevel;
import iuh.fit.week3_lab_phanhoaian_20012781.ids.CandidateSkillId;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "candidate_skills")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@IdClass(CandidateSkillId.class)
public class CandidateSkill {
    @Column(name = "more_infos",length = 1000)
    private String moreInfos;
    @Column(name = "skill_level")
    private SkillLevel skillLevel;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "skill_id")
    private Skill skill;
    @Id
    @ManyToOne
    @JoinColumn(name = "can_id")
    private Candidate candidate;
}
