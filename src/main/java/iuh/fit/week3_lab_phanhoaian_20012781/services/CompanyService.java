package iuh.fit.week3_lab_phanhoaian_20012781.services;

import iuh.fit.week3_lab_phanhoaian_20012781.models.Company;
import iuh.fit.week3_lab_phanhoaian_20012781.repositories.CompanyRepository;
import iuh.fit.week3_lab_phanhoaian_20012781.response.DataResponse;
import iuh.fit.week3_lab_phanhoaian_20012781.response.JobResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public DataResponse<Page<Company>> getAllCompanies(String filterQ, int page, int limit, String sortBy, String sortDirection) {
        // Tạo đối tượng Pageable để xác định trang và kích thước trang
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));

        // Gọi phương thức truy vấn cơ sở dữ liệu trong Repository để lấy dữ liệu sản phẩm với phân trang, sắp xếp và bộ lọc
        Page<Company> companiesPage;
        if (filterQ != null && !filterQ.isEmpty()) {
            companiesPage = companyRepository.findCompaniesByCompNameContainingIgnoreCase(filterQ, pageable);
        } else {
            companiesPage = companyRepository.findAll(pageable);
        }

        return DataResponse.<Page<Company>>builder()
                .data(companiesPage)
                .status(200)
                .message("Success")
                .build();
    }
}
