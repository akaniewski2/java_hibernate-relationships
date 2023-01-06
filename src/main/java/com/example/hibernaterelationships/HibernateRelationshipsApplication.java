package com.example.hibernaterelationships;

import com.example.hibernaterelationships.jpa.domain.*;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        entityManager.getTransaction().begin();

			Student student = new Student();
			Student student2 = new Student();



			student.setWantsNewsletter(false);
			entityManager.persist(student);


			student2.setWantsNewsletter(true);
			student2.setGender(Gender.MALE);


			Address address2 = new Address();
			address2.setCity("a");
			address2.setStreet("b");
			address2.setNumber("21/1");
			student2.setAddress(address2);

			student2.setGender(Gender.FEMALE);
			entityManager.persist(student2);

		//entityManager.getTransaction().commit();
		//entityManager.getTransaction().begin();

		//-- I sposob zapisujemy course
			Teacher teacher = new Teacher();
			teacher.setLastname("Dow");
			teacher.setFirstName("Aoo");
		    entityManager.persist(teacher);


			Course course;
			course = new Course("C#");
			course.setTeacher(teacher);
			entityManager.persist(course);

			course = new Course("Java");
			course.setTeacher(teacher);
			entityManager.persist(course);

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
		    entityManager.persist(teacher);


        entityManager.getTransaction().commit();
        entityManager.clear();

        Student foundStudent = entityManager.find(Student.class, 1L);

        System.out.printf("# :" + foundStudent);
        entityManager.close();

    }


}

