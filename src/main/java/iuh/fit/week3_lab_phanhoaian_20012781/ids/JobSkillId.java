package iuh.fit.week3_lab_phanhoaian_20012781.ids;

import iuh.fit.week3_lab_phanhoaian_20012781.models.Job;
import iuh.fit.week3_lab_phanhoaian_20012781.models.Skill;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class JobSkillId implements Serializable {
    private Job job;
    private Skill skill;
}