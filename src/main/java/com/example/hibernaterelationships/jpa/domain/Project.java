package com.example.hibernaterelationships.jpa.domain;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    //--I wersja
    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = new HashSet<>();

    //--II wersja - niewytestowana
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name="EMLOYEE_PROJECT",
//            joinColumns = @JoinColumn(name="project_id ",referencedColumnName = "ID"),
//            inverseJoinColumns = @JoinColumn(name="employee_id",referencedColumnName ="ID" )
//    )
//    private Set<Employee> employees = new HashSet<>();

}
