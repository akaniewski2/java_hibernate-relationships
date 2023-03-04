package com.example.hibernaterelationships.jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String fistrName;
    String lastName;




  // -- I wersja

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name="EMLOYEE_PROJECT",
//            joinColumns = @JoinColumn(name="employee_id",referencedColumnName = "ID"),
//            inverseJoinColumns = @JoinColumn(name="project_id",referencedColumnName ="ID" )
//    )
//    private Set<Project> projects= new HashSet<>();


//    --II werja -- niewytestowana
//    @ManyToMany(mappedBy = "employees",cascade = CascadeType.ALL)
//    private Set<Project> projects= new HashSet<>();

//  -- IIb wersja - DZIAŁA !! ZAPISUJE W DWIE STRONY klasa EMPLOYEE - PROJECT

        @ManyToMany( cascade = CascadeType.ALL)
        @JoinTable(
        name="EMLOYEE_PROJECT",
        joinColumns = @JoinColumn(name="employee_id ",referencedColumnName = "ID"),
        inverseJoinColumns = @JoinColumn(name="project_id",referencedColumnName ="ID" ))
        private Set<Project> projects= new HashSet<>();


    public void addProject(Project project) {
        this.projects.add(project);
    }
    public void removeProject(Project project) {
        this.projects.remove(project);
        project.getEmployees().remove(this);
    }


}
