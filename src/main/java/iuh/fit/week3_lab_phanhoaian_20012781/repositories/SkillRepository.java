package iuh.fit.week3_lab_phanhoaian_20012781.repositories;

import iuh.fit.week3_lab_phanhoaian_20012781.models.Skill;
import iuh.fit.week3_lab_phanhoaian_20012781.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    @Query(value = """
      select s from Skill s inner join JobSkill jk\s
      on s.id = jk.skill.id\s
      where jk.job.id = :jobId \s
      """)
    List<Skill> findAllSkillByJob(Long jobId);
}
