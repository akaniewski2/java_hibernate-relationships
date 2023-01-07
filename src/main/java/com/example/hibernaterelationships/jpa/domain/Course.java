package com.example.hibernaterelationships.jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Course {

    public Course(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //    I - wersja join column jest w Teacher, a w Course nie ma odwołania do Teacher


    //II - werja - preferowna aby @JoinColumn było po stronie FK
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST,optional = false /*optional = false nie pozowli zapisac course bez teacher */)
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
    private Teacher teacher;

    @OneToOne(mappedBy = "course",cascade = CascadeType.PERSIST)
    private CourseMaterial courseMaterial;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        this.students.add(student);
    }





}
