package iuh.fit.week3_lab_phanhoaian_20012781.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import iuh.fit.week3_lab_phanhoaian_20012781.enums.SkillLevel;
import iuh.fit.week3_lab_phanhoaian_20012781.ids.JobSkillId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_skills")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(JobSkillId.class)
public class JobSkill {
    @Column(name = "more_infos",length = 1000)
    private String moreInfos;

    @Column(name = "skill_level")
    private SkillLevel skillLevel;

    @Id
    @ManyToOne()
    @JoinColumn(name = "job_id")
    @JsonIgnore
    private Job job;

    @Id
    @ManyToOne()
    @JoinColumn(name = "skill_id")
    @JsonIgnore
    private Skill skill;
}
