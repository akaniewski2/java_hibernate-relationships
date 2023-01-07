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
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;

    //    I - wersja join column jest w Teacher, a w Course nie ma odwołania do Teacher
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
//    private List<Course> courses;

    //II - werja
    @OneToMany(mappedBy = "teacher",cascade = CascadeType.PERSIST)
    private List<Course> courses;


}