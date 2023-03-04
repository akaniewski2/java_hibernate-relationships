package com.example.hibernaterelationships.jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

    //--I wersja
//    @ManyToMany(mappedBy = "projects",cascade = CascadeType.PERSIST)
//    private Set<Employee> employees = new HashSet<>();

    //--IIb wersja - DZIAŁA !! ZAPISUJE W DWIE STRONY
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="EMLOYEE_PROJECT",
            joinColumns = @JoinColumn(name="project_id ",referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="employee_id",referencedColumnName ="ID" )
    )
    private Set<Employee> employees = new HashSet<>();

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }
}
