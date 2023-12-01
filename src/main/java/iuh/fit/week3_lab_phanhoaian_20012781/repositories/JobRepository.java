package iuh.fit.week3_lab_phanhoaian_20012781.repositories;

import iuh.fit.week3_lab_phanhoaian_20012781.models.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends JpaRepository<Job, Long> {
    Page<Job> findJobsByJobNameContainingIgnoreCase(String productName, Pageable pageable);

    @Query(value = """
      select j from Job j inner join JobSkill jk\s
      on j.id = jk.job.id\s
      inner join Skill s\s
      on s.id = jk.skill.id\s
      where s.id in :ids \s
      """)
    Page<Job> findAllByCandidateSkills(long[] ids, Pageable pageable);
}
