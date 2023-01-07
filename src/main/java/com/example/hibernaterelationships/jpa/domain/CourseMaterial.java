package com.example.hibernaterelationships.jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class CourseMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String url;

    public CourseMaterial( String url, Course course) {

        this.url = url;
        this.course = course;
    }

    @OneToOne(optional = true)
    @JoinColumn(name = "COURSE_ID")
    private Course course;
}
