package iuh.fit.week3_lab_phanhoaian_20012781.resource;

import iuh.fit.week3_lab_phanhoaian_20012781.models.Company;
import iuh.fit.week3_lab_phanhoaian_20012781.response.DataResponse;
import iuh.fit.week3_lab_phanhoaian_20012781.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CompanyResource {
    private final CompanyService companyService;

    @GetMapping("/companies")
    public ResponseEntity<DataResponse<Page<Company>>> getAllCompanies(
            @RequestParam(value = "filter[q]", defaultValue = "") String filterQ,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "20") int limit,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection
    ) {
        return ResponseEntity.ok(companyService.getAllCompanies(filterQ, page, limit, sortBy, sortDirection));
    }
}
