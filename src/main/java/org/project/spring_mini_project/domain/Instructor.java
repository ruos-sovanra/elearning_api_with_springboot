package org.project.spring_mini_project.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "instructors")
@Data
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    private String family_name;
    private String github;
    private String given_name;
    private Boolean is_blocked;
    private Boolean is_deleted;
    private String job_title;
    private String linked_in;

    @Column(unique = true, nullable = false,length = 30)
    private String national_id_card;
    private String profile;

    //relationship user_id
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String website;

    //relationship courses
    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Course> courses;

}
