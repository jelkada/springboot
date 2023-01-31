package com.jelkada.basic;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddStudent {

	public static void main(String[] args) {
				
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session= factory.getCurrentSession();

		try {
			// example: add students and save them to the DB
			session.beginTransaction();
			Student student1 = new Student("Jimmy", "Elkada", "jimmy.elkada@nov.com");		
			Student student2 = new Student("Johnny", "Smith", "jmsith@gmail.com");
			Student student3 = new Student("Susan", "Silverton", "susan123@gmail.com");
			session.save(student1);
			session.save(student2);
			session.save(student3);
			session.getTransaction().commit();
			System.out.println("\nGet student id after save: " + student1.getId());

			// example: get a student from the DB by student id
			session = factory.getCurrentSession();
			session.beginTransaction();
			Student dbStudent = session.get(Student.class, 1);
			// Student dbStudent = session.get(Student.class, student1.getId());
			session.getTransaction().commit();
			System.out.println("\nGet student by student id: " + dbStudent);
			
			// example: get all table rows using createQuery
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Student> theStudents = session.createQuery("from Student").list(); 
			session.getTransaction().commit();
			System.out.println("\nGet all student rows: " + theStudents);
			
			// example: get rows using createQuery with where
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Student> studentByName = session.createQuery("from Student s where s.firstName = 'Susan' or s.lastName = 'Elkada' ").list(); 
			session.getTransaction().commit();
			System.out.println("\nGet student by last name: " + studentByName);

			// example: get rows using createQuery with where like
			session = factory.getCurrentSession();
			session.beginTransaction();
			List<Student> studentEmailLike = session.createQuery("from Student s where s.email like '%gmail.com'").list(); 
			session.getTransaction().commit();
			System.out.println("\nGet student where email like: " + studentEmailLike);

		}
		catch (Exception exc) {
			System.out.println("Exception occured: exc: " + exc);
		}
		finally {
			session.close();
		}
	}
}
