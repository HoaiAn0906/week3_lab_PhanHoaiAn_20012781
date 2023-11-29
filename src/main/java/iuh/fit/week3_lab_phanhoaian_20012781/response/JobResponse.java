package iuh.fit.week3_lab_phanhoaian_20012781.response;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import iuh.fit.week3_lab_phanhoaian_20012781.models.Company;
import iuh.fit.week3_lab_phanhoaian_20012781.models.JobSkill;
import iuh.fit.week3_lab_phanhoaian_20012781.models.Skill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class JobResponse {
    private long id;
    private String jobDesc;
    private String jobName;
    private Company company;
    private List<Skill> skills;
}
