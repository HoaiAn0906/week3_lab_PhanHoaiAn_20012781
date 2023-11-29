package iuh.fit.week3_lab_phanhoaian_20012781.services;

import iuh.fit.week3_lab_phanhoaian_20012781.models.Job;
import iuh.fit.week3_lab_phanhoaian_20012781.models.Skill;
import iuh.fit.week3_lab_phanhoaian_20012781.repositories.JobRepository;
import iuh.fit.week3_lab_phanhoaian_20012781.repositories.SkillRepository;
import iuh.fit.week3_lab_phanhoaian_20012781.response.DataResponse;
import iuh.fit.week3_lab_phanhoaian_20012781.response.JobResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;

    public DataResponse<Page<JobResponse>> getAllJobs(String filterQ, int page, int limit, String sortBy, String sortDirection) {
        // Tạo đối tượng Pageable để xác định trang và kích thước trang
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));

        // Gọi phương thức truy vấn cơ sở dữ liệu trong Repository để lấy dữ liệu sản phẩm với phân trang, sắp xếp và bộ lọc
        Page<Job> jobsPage;
        if (filterQ != null && !filterQ.isEmpty()) {
            jobsPage = jobRepository.findJobsByJobNameContainingIgnoreCase(filterQ, pageable);
        } else {
            jobsPage = jobRepository.findAll(pageable);
        }

        return DataResponse.<Page<JobResponse>>builder()
                .data(jobsPage.map(job -> JobResponse.builder()
                        .id(job.getId())
                        .jobDesc(job.getJobDesc())
                        .jobName(job.getJobName())
                        .company(job.getCompany())
                        .skills(skillRepository.findAllSkillByJob(job.getId()))
                        .build()))
                .status(200)
                .message("Success")
                .build();
    }
}
