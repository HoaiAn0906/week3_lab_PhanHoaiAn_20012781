package iuh.fit.week3_lab_phanhoaian_20012781.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 20,name = "id")
    private long id;
    @Column(length = 150)
    private String street;
    @Column(length = 50)
    private String city;
    @Column(length = 6)
    private CountryCode country;
    @Column(length = 20)
    private String number;
    @Column(length = 7,name = "zipcode")
    private String zipCode;
    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Candidate candidate;
    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Company company;

    public Address(String street, String city, CountryCode country, String number, String zipCode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.number = number;
        this.zipCode = zipCode;
    }
}
