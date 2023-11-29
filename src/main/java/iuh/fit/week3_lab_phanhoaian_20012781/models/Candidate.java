package iuh.fit.week3_lab_phanhoaian_20012781.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "candidates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 20,name = "id")
    private long id;
    private LocalDate dob;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(length = 15,unique = true)
    private String phone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address")
    private Address address;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidate",fetch = FetchType.EAGER)
    private List<CandidateSkill> candidateSkills;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidate")
    private List<Experience> experiences;

    public Candidate(LocalDate dob, String email, String password, String fullName, String phone, Address address) {
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
    }
    public Candidate(long id) {
        this.id = id;
    }
}
