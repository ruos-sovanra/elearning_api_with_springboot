package org.project.spring_mini_project.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Entity
@Table(name = "countries")
@Data
@Accessors(chain = true)
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String flag;

    @Column(nullable = false,length = 10)
    private String iso;

    @Column(nullable = false,length = 60)
    private String name;

    private String nice_name;

    @Column(nullable = false)

    private Integer num_code;

    @Column(nullable = false)

    private Integer phone_code;

    //relationship city
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<City> cities;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> users;


    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", flag='" + flag + '\'' +
                ", iso='" + iso + '\'' +
                ", name='" + name + '\'' +
                ", nice_name='" + nice_name + '\'' +
                ", num_code=" + num_code +
                ", phone_code=" + phone_code +
                '}';
    }
}
