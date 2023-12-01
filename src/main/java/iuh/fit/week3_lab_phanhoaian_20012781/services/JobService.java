package iuh.fit.week3_lab_phanhoaian_20012781.services;

import iuh.fit.week3_lab_phanhoaian_20012781.models.*;
import iuh.fit.week3_lab_phanhoaian_20012781.repositories.CompanyRepository;
import iuh.fit.week3_lab_phanhoaian_20012781.repositories.JobRepository;
import iuh.fit.week3_lab_phanhoaian_20012781.repositories.SkillRepository;
import iuh.fit.week3_lab_phanhoaian_20012781.repositories.UserRepository;
import iuh.fit.week3_lab_phanhoaian_20012781.request.JobRequest;
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
import java.util.Optional;

@Service
@AllArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

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

    public DataResponse<Page<JobResponse>> getAllJobs(String filterQ, int page, int limit, String sortBy, String sortDirection, String email) {
        System.out.println(email);
        Optional<User> user = userRepository.findByEmail(email);
        Candidate candidate = new Candidate();
        candidate.setId(user.get().getCandidate().getId());

        List<Skill> candidateSkills = skillRepository.findAllSkillByCandidate(candidate.getId());
        long[] ids = new long[candidateSkills.size()];
        for (int i = 0; i < candidateSkills.size(); i++) {
            ids[i] = candidateSkills.get(i).getId();
        }

        System.out.println("ids" + ids);

        // Tạo đối tượng Pageable để xác định trang và kích thước trang
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));

        // Gọi phương thức truy vấn cơ sở dữ liệu trong Repository để lấy dữ liệu sản phẩm với phân trang, sắp xếp và bộ lọc
        Page<Job> jobsPage;
        if (filterQ != null && !filterQ.isEmpty()) {
            jobsPage = jobRepository.findJobsByJobNameContainingIgnoreCase(filterQ, pageable);
        } else {
            jobsPage = jobRepository.findAllByCandidateSkills(ids, pageable);
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

    public DataResponse<JobResponse> createJob(JobRequest jobRequest) {
        Job job = new Job();
        job.setJobName(jobRequest.getJobName());
        job.setJobDesc(jobRequest.getJobDesc());
        Company company = companyRepository.findById(jobRequest.getCompany()).orElse(null);
        job.setCompany(company);
        //save job_skills
        List<JobSkill> jobSkills = new ArrayList<>();
        for (long skillId : jobRequest.getSkills()) {
            Skill skill = skillRepository.findById(skillId).orElse(null);
            JobSkill jobSkill = new JobSkill();
            jobSkill.setJob(job);
            jobSkill.setSkill(skill);
            jobSkills.add(jobSkill);
        }
        job.setJobSkills(jobSkills);
        jobRepository.save(job);

        return DataResponse.<JobResponse>builder()
                .data(JobResponse.builder()
                        .id(job.getId())
                        .jobDesc(job.getJobDesc())
                        .jobName(job.getJobName())
                        .company(job.getCompany())
                        .skills(skillRepository.findAllSkillByJob(job.getId()))
                        .build())
                .status(200)
                .message("Success")
                .build();
    }

    public DataResponse<JobResponse> deleteJob(long id) {
        Job job = jobRepository.findById(id).orElse(null);
        jobRepository.delete(job);

        return DataResponse.<JobResponse>builder()
                .data(JobResponse.builder()
                        .id(job.getId())
                        .jobDesc(job.getJobDesc())
                        .jobName(job.getJobName())
                        .company(job.getCompany())
                        .skills(skillRepository.findAllSkillByJob(job.getId()))
                        .build())
                .status(200)
                .message("Success")
                .build();
    }
}
