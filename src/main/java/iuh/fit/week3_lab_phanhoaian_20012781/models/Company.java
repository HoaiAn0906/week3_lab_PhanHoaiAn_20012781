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
    private String comp_name;
    private String phone;
    @Column(name = "web_url")
    private String webUrl;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "address")
    private Address address;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "company")
    @JsonIgnore
    private List<Job> jobs;

    public Company(String about, String email, String password, String comp_name, String phone, String webUrl, Address address) {
        this.about = about;
        this.email = email;
        this.password = password;
        this.comp_name = comp_name;
        this.phone = phone;
        this.webUrl = webUrl;
        this.address = address;
    }
}
