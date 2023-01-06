package com.example.hibernaterelationships.jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Course {

    @Id
    private Long id;
    private String name;

    //    I - wersja join column jest w Teacher, a w Course nie ma odwołania do Teacher


    //II - werja - preferowna aby @JoinColumn było po stronie FK
    @ManyToOne
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
    private Teacher teacher;

}
