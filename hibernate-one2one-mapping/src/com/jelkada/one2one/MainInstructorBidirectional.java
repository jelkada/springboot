package com.jelkada.one2one;

	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.cfg.Configuration;

	public class MainInstructorBidirectional {

		public static void main(String[] args) {
					
			SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
			
			Session session= factory.getCurrentSession();

			try {
				// example: get the instructor object from the instructor detail data
				session.beginTransaction();
				int theId = 3; // assume we got the id earlier
				InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
				System.out.println("\nGet tempInstructorDetail from the DB: " + tempInstructorDetail);
				if (tempInstructorDetail != null) {
					System.out.println("\nGet Instructor from InstructorDetail: " + tempInstructorDetail.getInstructor());
				};
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
