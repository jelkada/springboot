package com.jelkada.one2one;

	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.cfg.Configuration;

	public class MainCreateInstructor {

		public static void main(String[] args) {
					
			SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
			
			Session session= factory.getCurrentSession();

			try {
				// example: create 2 instructors and their details
				session.beginTransaction();
				
				Instructor instructor1 = new Instructor("John", "Smith", "jsmith@yahoo.com");
				InstructorDetail instructorDetail1 = new InstructorDetail("http://jsmith89/youtube", "Coding in Java");
				instructor1.setInstructorDetail(instructorDetail1);
				session.save(instructor1);
				
				Instructor instructor2 = new Instructor("Dave", "Wagner", "davewagner12@yahoo.com");
				InstructorDetail instructorDetail2 = new InstructorDetail("http://dave12/youtube", "Coding in C++");
				instructor2.setInstructorDetail(instructorDetail2);
				session.save(instructor2);
				
				session.getTransaction().commit();
				System.out.println("\nBoth Instructor and InstructorDetail are save due to cascade");
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
