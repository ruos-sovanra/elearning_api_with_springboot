package org.project.spring_mini_project.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "courses")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String alias;
    private String description;
    private Boolean is_deleted;
    private Boolean is_free;
    private String thumbnail;
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cat_id")
    private Categories categories;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ins_id")
    private Instructor instructor;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", description='" + description + '\'' +
                ", is_deleted=" + is_deleted +
                ", is_free=" + is_free +
                ", thumbnail='" + thumbnail + '\'' +
                ", title='" + title + '\'' +
                // Do not include the 'categories' field in the toString method
                '}';
    }

}
