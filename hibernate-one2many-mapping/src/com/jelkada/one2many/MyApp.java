package com.jelkada.one2many;

	import java.util.ArrayList;
	import java.util.List;

	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.cfg.Configuration;

	public class MyApp {

		public static void main(String[] args) {
					
			SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
			
			Session session= factory.getCurrentSession();

			try {
				// example: create an instructor and instructor details
				session.beginTransaction();
				
				Instructor instructor1 = new Instructor("Jeff", "Bleak", "jeff23@yahoo.com");
				InstructorDetail instructorDetail1 = new InstructorDetail("http://jeff24/youtube", "Sports and reading");
				instructor1.setInstructorDetail(instructorDetail1);
				session.save(instructor1);
								
				session.getTransaction().commit();
				System.out.println("\nInstructor and InstructorDetail are save due to cascade");
				
				// example: create 2 courses and assign to them the same instructor
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				Course course1 = new Course("Angular advanced concepts");
				Course course2 = new Course("Angular and Spring Boot");
				
				course1.setInstructor(instructor1);
				course2.setInstructor(instructor1);
				
				List<Course> courses = new ArrayList<Course>();
				courses.add(course1);
				courses.add(course2);
				// set bidirectional relations
				instructor1.setCourses(courses);
				session.save(course1);	
				session.save(course2);	
				
				session.getTransaction().commit();
				
				System.out.println("\nThe courses are: " + courses);
				System.out.println("\nThe courses by instructor are: " + instructor1.getCourses());
				
				// example: delete one course without cascading delete
				session = factory.getCurrentSession();
				session.beginTransaction();
				
				// List<Course> coursesResult = session.createQuery("from Course where title='Angular advanced concepts'").list();
				
				Course deleteCourse = (Course) session.createQuery("from Course where title='Angular advanced concepts'").getSingleResult();
				System.out.println("\ndeleteCourse: " + deleteCourse);
//				if (deleteCourse != null) {
//					session.delete(deleteCourse);
//				};

//				System.out.println("\nThe courses are: " + coursesResult);
//				if (coursesResult.size() > 0) {
//					session.delete(coursesResult.get(0));
//				};
				session.getTransaction().commit();
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
