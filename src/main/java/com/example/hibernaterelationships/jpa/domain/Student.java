package com.example.hibernaterelationships.jpa.domain;

import com.example.hibernaterelationships.jpa.domain.converters.YesNoBooleanConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @Temporal(TemporalType.DATE)
//    private Date birthDate;

    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String firstName;
    private String lastName;
    @Convert(converter = YesNoBooleanConverter.class)
    private boolean wantsNewsletter;

    @Embedded
    //We should note that, since JPA 2.2, the @AttributeOverride annotation is repeatable.
   // @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "ST_STREET"))//,
            @AttributeOverride(name = "number", column = @Column(name = "ST_NUMBER"))//,
            @AttributeOverride(name = "city", column = @Column(name = "ST_CITY"))
   // })
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="STUDENTS_COURSES",
            joinColumns = @JoinColumn(name="COURSE_ID",referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="STUDENT_ID",referencedColumnName ="ID" )
    )
    private List<Course> courses;
  //  private List<Student> students;

    public Long id() {
        return id;
    }






}
