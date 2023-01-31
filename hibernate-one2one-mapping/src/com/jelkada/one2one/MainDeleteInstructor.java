package com.jelkada.one2one;

	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.cfg.Configuration;

	public class MainDeleteInstructor {

		public static void main(String[] args) {
					
			SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
			
			Session session= factory.getCurrentSession();

			try {
				// example: add students and save them to the DB
				session.beginTransaction();
				Instructor tempInstructor = session.get(Instructor.class, 1);
				if (tempInstructor != null) {
					session.delete(tempInstructor);
				};
				session.getTransaction().commit();
				System.out.println("\nBoth Instructor and InstructorDetail are deleted due to cascade");
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
