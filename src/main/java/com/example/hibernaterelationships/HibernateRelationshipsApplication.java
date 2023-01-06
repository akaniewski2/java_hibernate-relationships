package com.example.hibernaterelationships;

import com.example.hibernaterelationships.jpa.domain.Address;
import com.example.hibernaterelationships.jpa.domain.Gender;
import com.example.hibernaterelationships.jpa.domain.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

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


				student.setId(1L);
				student.setWantsNewsletter(false);
				entityManager.persist(student);

				student2.setId(2L);
		    	student2.setWantsNewsletter(true);
				student2.setGender(Gender.MALE);


                Address address2 = new Address();
				address2.setCity("a");
				address2.setStreet("b");
				address2.setNumber("21/1");
				student2.setAddress(address2);

				student2.setGender(Gender.FEMALE);


				entityManager.persist(student2);





			entityManager.getTransaction().commit();
			entityManager.clear();

			Student foundStudent = entityManager.find(Student.class, 1L);

			System.out.printf("# :"+foundStudent);
			entityManager.close();

		}


	}

