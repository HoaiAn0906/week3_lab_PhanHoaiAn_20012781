package iuh.fit.week3_lab_phanhoaian_20012781.resource;

import iuh.fit.week3_lab_phanhoaian_20012781.models.Job;
import iuh.fit.week3_lab_phanhoaian_20012781.request.JobRequest;
import iuh.fit.week3_lab_phanhoaian_20012781.response.DataResponse;
import iuh.fit.week3_lab_phanhoaian_20012781.response.JobResponse;
import iuh.fit.week3_lab_phanhoaian_20012781.services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class JobResource {
    private final JobService jobService;

    @GetMapping("/jobs")
    public ResponseEntity<DataResponse<Page<JobResponse>>> getAllJobs(
            @RequestParam(value = "filter[q]", defaultValue = "") String filterQ,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "20") int limit,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection
    ) {
        return ResponseEntity.ok(jobService.getAllJobs(filterQ, page, limit, sortBy, sortDirection));
    }

    @PostMapping("/jobs")
    public ResponseEntity<DataResponse<JobResponse>> createJob(
            @RequestBody JobRequest jobRequest
            ) {
        return ResponseEntity.ok(jobService.createJob(jobRequest));
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<DataResponse<JobResponse>> deleteJob(
            @PathVariable("id") long id
    ) {
        return ResponseEntity.ok(jobService.deleteJob(id));
    }
}
