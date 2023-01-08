package com.example.hibernaterelationships;

import com.example.hibernaterelationships.jpa.domain.*;


import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.List.of;

//https://stackabuse.com/guide-to-jpa-with-hibernate-basic-mapping/
//https://stackabuse.com/a-guide-to-jpa-with-hibernate-relationship-mapping/

/*
Domyslne wartości połączeń

OneToOne	EAGER
OneToMany	LAZY
ManyToOne	EAGER
ManyToMany	LAZY

* */


@SpringBootApplication
public class HibernateRelationshipsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateRelationshipsApplication.class, args);


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("guide-to-jpa-with-hibernate");
        EntityManager em = entityManagerFactory.createEntityManager();


        em.getTransaction().begin();

			Student student = new Student();
			Student student2 = new Student();



			student.setWantsNewsletter(false);
			em.persist(student);


			student2.setWantsNewsletter(true);
			student2.setGender(Gender.MALE);


			Address address2 = new Address();
			address2.setCity("a");
			address2.setStreet("b");
			address2.setNumber("21/1");
			student2.setAddress(address2);

			student2.setGender(Gender.FEMALE);
			em.persist(student2);

		//em.getTransaction().commit();
		//em.getTransaction().begin();

		//-- @ManyToOne ------------------------------------------------------


		//-- I sposob zapisujemy course
			Teacher teacher = new Teacher();
			teacher.setLastName("Dow");
			teacher.setFirstName("Aoo");
		    em.persist(teacher);


			Course course;
			course = new Course("C#");
			course.setTeacher(teacher);
			em.persist(course);

			course = new Course("Java");
			course.setTeacher(teacher);
			em.persist(course);

		//-- II sposob zapisujemy traeacher

			List<Course> courses = new ArrayList<>();
			course = new Course("VBA");
		 	course.setTeacher(teacher);
			courses.add(course);

			course = new Course("SQL");
			course.setTeacher(teacher);
			courses.add(course);

			System.out.println(courses);

			teacher.setCourses(courses);
		    em.persist(teacher);

			//-- @OneToOne ------------------------------------------------

			//---I sposob -----------------
			CourseMaterial courseMaterial ;
			courseMaterial=new CourseMaterial("http:\\wikiMath",course);
			//courseMaterial.setCourse(course);
			em.persist(courseMaterial);


			//---II sposob -----------------
		    course = em.find(Course.class, 1L);
			courseMaterial=new CourseMaterial("http:\\wikiPol",course);
			course.setCourseMaterial(courseMaterial);
			em.persist(course);


			//-- @ManyToMany ------------------------------------------

            //tworzymy studentów
			Student student1 = new Student();
			student1.setFirstName("John");
			student1.setLastName("Doe");
			student1.setBirthDate(LocalDate.of(2000, Month.FEBRUARY,18));
			student1.setWantsNewsletter(true);
			student1.setAddress(new Address("Baker Street","221B","London"));
			em.persist(student1);

			//Student student2 = new Student();
			student2.setFirstName("Will");
			student2.setLastName("Doe");
			student2.setBirthDate(LocalDate.of(2001, Month.APRIL,4));
			student2.setWantsNewsletter(true);
			student2.setAddress(new Address("Washington Avenue","123","Oxford"));
			em.persist(student2);

			//tworze teachera
			teacher.setFirstName("Jane");
			teacher.setLastName("Low");
			em.persist(teacher);

			//tworze courses i przypisuje studentow
			course = new Course("Java 101");
			course.setTeacher(teacher);
			course.setStudents(new ArrayList<>(of(student1))); //w tym miejscu to nie dziala
			course.setStudents(new ArrayList<>(of(student2)));;
			em.persist(course);

			course = new Course("SQL 101");
			course.setStudents(new ArrayList<>(of(student1)));//w tym miejscu to nie dziala
			course.setTeacher(teacher);
			em.persist(course);

			//przypisuje studentow do courses
//			course.addStudent(student1);
//			course.addStudent(student2);
//			em.persist(course);


		//--- EMPLOYEE-PROJECT

		Employee employee1 = new Employee();
		employee1.setFistrName("e1");
		employee1.setLastName("e1");

		Employee employee2 = new Employee();
		employee1.setFistrName("e2");
		employee1.setLastName("e2");



		Project project1 = new Project();
		project1.setName("p1");

		Project project2 = new Project();
		project2.setName("p2");


		employee1.addProject(project1);
		employee1.addProject(project2);

		employee2.addProject(project1);
		employee2.addProject(project2);

		em.persist(employee1);
		em.persist(employee2);


		em.getTransaction().commit();
        em.clear();

        Student foundStudent = em.find(Student.class, 1L);

        System.out.printf("# :" + foundStudent);
        em.close();

    }


}

