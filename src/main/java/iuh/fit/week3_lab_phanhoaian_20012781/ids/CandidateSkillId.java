package iuh.fit.week3_lab_phanhoaian_20012781.ids;

import iuh.fit.week3_lab_phanhoaian_20012781.models.Candidate;
import iuh.fit.week3_lab_phanhoaian_20012781.models.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CandidateSkillId implements Serializable {
    private Candidate candidate;
    private Skill skill;
}
