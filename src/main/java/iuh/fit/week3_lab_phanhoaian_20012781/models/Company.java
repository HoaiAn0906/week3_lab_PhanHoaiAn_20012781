package iuh.fit.week3_lab_phanhoaian_20012781.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 20,name = "id")
    private long id;
    @Column(length = 2000)
    private String about;
    private String email;
    private String password;
    @Column(name = "comp_name")
    private String compName;
    private String phone;
    @Column(name = "web_url")
    private String webUrl;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "address")
    private Address address;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "company")
    @JsonIgnore
    private List<Job> jobs;
}
