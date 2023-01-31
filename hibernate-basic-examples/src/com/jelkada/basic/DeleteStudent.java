package com.jelkada.basic;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudent {

	public static void main(String[] args) {
				
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		Session session= factory.getCurrentSession();

		try {
			// example: get a student and delete it using session
			session.beginTransaction();
			List<Student> studentsResult = session.createQuery("from Student where firstName='Susan'").list();
			displayStudents(studentsResult);
			System.out.println("\nGet students query: studentsResult.size(): " + studentsResult.size());
			if (studentsResult.size() > 0) {
				session.delete(studentsResult.get(0));
			};
			session.getTransaction().commit();
			
			// example: delete students using a session query
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("delete from Student where lastName='Elkada'").executeUpdate();
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
		System.out.println("\ndisplayStudents():");
		for (Student stud : theStudents) {
			System.out.println(stud);
		}
	}
}
