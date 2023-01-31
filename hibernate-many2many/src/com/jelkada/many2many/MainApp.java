package com.jelkada.many2many;

	import java.util.List;

	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.cfg.Configuration;

	public class MainApp {

		public static void main(String[] args) {
					
			SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
			
			Session session= factory.getCurrentSession();

			try {
				// example: create a course and 2 students and link them
				session.beginTransaction();
				
				Course course1 = new Course("Angular and Spring Boot");
				session.save(course1);
				
				Student student1 = new Student("Jay", "Mandelsom", "jay_man@yahoo.com");
				Student student2 = new Student("Dave", "Birkman", "dbk22@yahoo.com");
				course1.addStudent(student1);
				course1.addStudent(student2);
				session.save(student1);
				session.save(student2);
								
				session.getTransaction().commit();
				System.out.println("\nAdded to the DB: course1 and its students: student1 and student2");
				
				// example: add 2 courses to student1
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				Course course2 = new Course("Master C++");
				Course course3 = new Course("Unit Testing in Java");
				session.save(course2);	
				session.save(course3);
				
				student1.addCourse(course2);
				student1.addCourse(course3);
				session.save(student1);
				
				session.getTransaction().commit();
				System.out.println("\nAdded to the DB: course2 and course3 added to student1");
				
				// example: delete course1 and related references
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				Course angularCourse = (Course) session.createQuery("from Course where title='Angular and Spring Boot'").getSingleResult();
				session.delete(angularCourse);
				List<Course> remainingCourses = session.createQuery("from Course").list();

				session.getTransaction().commit();
				System.out.println("\nCourse deleted, remainingCourses: " + remainingCourses);
				
			}
			catch (Exception exc) {
				System.out.println("Exception occured: exc: " + exc);
			}
			finally {
				session.close();
				factory.close();
			}
		}
	}
