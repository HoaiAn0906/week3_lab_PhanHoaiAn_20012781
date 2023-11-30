package iuh.fit.week3_lab_phanhoaian_20012781.repositories;

import iuh.fit.week3_lab_phanhoaian_20012781.models.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Page<Company> findCompaniesByCompNameContainingIgnoreCase(String companyName, Pageable pageable);
}
