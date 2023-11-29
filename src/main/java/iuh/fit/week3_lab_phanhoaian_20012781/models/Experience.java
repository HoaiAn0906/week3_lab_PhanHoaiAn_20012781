package iuh.fit.week3_lab_phanhoaian_20012781.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "experiences")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "to_date")
    private LocalDate toDate;
    @ManyToOne
    @JoinColumn(name = "can_id")
    private Candidate candidate;
    @Column(name = "from_date")
    private LocalDate fromDate;
    @Column(name = "company",length = 120)
    private String companyName;
    @Column(length = 100)
    private String role;
    @Column(name = "work_desc",length = 400)
    private String workDescription;

    public Experience(LocalDate toDate, Candidate candidate, LocalDate fromDate, String companyName, String role, String workDescription) {
        this.toDate = toDate;
        this.candidate = candidate;
        this.fromDate = fromDate;
        this.companyName = companyName;
        this.role = role;
        this.workDescription = workDescription;
    }
}
