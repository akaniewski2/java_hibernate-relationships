package com.example.hibernaterelationships.jpa.domain;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String fistrName;
    String lastName;




  // -- I wersja

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="EMLOYEE_PROJECT",
            joinColumns = @JoinColumn(name="employee_id",referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="project_id",referencedColumnName ="ID" )
    )
    private Set<Project> projects= new HashSet<>();


    //--II werja -- niewytestowana
//    @ManyToMany(mappedBy = "employees")
//    private Set<Project> projects= new HashSet<>();

    public void addProject(Project project) {
        this.projects.add(project);
    }

}
