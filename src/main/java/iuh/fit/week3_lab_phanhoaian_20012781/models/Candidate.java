package iuh.fit.week3_lab_phanhoaian_20012781.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "candidates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 20,name = "id")
    private long id;

    private LocalDate dob;

    @Column(length = 15,unique = true)
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidate",fetch = FetchType.EAGER)
    private List<CandidateSkill> candidateSkills;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidate")
    private List<Experience> experiences;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                ", candidateSkills=" + candidateSkills +
                ", experiences=" + experiences +
                '}';
    }
}
