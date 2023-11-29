package iuh.fit.week3_lab_phanhoaian_20012781.repositories;

import iuh.fit.week3_lab_phanhoaian_20012781.models.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
    Page<Job> findJobsByJobNameContainingIgnoreCase(String productName, Pageable pageable);
}
