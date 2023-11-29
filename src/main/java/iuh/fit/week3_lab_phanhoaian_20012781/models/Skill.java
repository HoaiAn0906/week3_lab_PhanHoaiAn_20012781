package iuh.fit.week3_lab_phanhoaian_20012781.models;

import iuh.fit.week3_lab_phanhoaian_20012781.enums.SkillType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "skills")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",length = 20)
    private long id;

    @Column(name = "skill_desc",length = 300)
    private String skillDescription;

    @Column(name = "skill_name",length = 150)
    private String skillName;

    private SkillType type;

    @OneToMany(mappedBy = "skill",fetch = FetchType.LAZY)
    private List<JobSkill> jobSkills;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return id == skill.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}