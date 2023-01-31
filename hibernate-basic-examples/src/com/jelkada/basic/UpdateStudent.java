package com.jelkada.basic;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudent {

	public static void main(String[] args) {
				
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session= factory.getCurrentSession();

		try {
			// example: add a new student
			Student stud1 = new Student("David", "Brown", "david_br@gmail.com");
			session.beginTransaction();
			System.out.println("\nSave the new student object");
			session.save(stud1);
			session.getTransaction().commit();
			System.out.println("\nGet the student id: stud1.getId(): " + stud1.getId());

			// example: update the student first name via the setter
			session = factory.getCurrentSession();
			session.beginTransaction();
			Student stud2 = session.get(Student.class, stud1.getId());
			stud2.setFirstName("John");
			session.getTransaction().commit();
			System.out.println("\nUpdate: stud2: " + stud2);

			// example: update a student email using createQuery
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("update Student set email='test@yahoo.com' where firstName='Susan'").executeUpdate();
			session.getTransaction().commit();			
			
			// example: get all users from the student table 
			session = factory.getCurrentSession();
			session.beginTransaction();			
			List<Student> theStudents = session.createQuery("from Student").list();
			session.getTransaction().commit();
			
			displayStudents(theStudents);	
		}
		catch (Exception exc) {
			session.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		System.out.println("\ndisplayStudents(): theStudents:");
		for (Student stud : theStudents) {
			System.out.println(stud);
		}
	}
}
