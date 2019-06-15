package hibernate.many.to.many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * BookManager.java A Hibernate hello world program
 * 
 * @author www.codejava.net
 *
 */
public class BookManager {
	protected SessionFactory sessionFactory;

	protected void setup() {

		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	protected void exit() {
		sessionFactory.close();
	}

	protected void create() {
		UserDetails userDetails = new UserDetails();
		userDetails.setName("Kedar");

		Vehicle vehicle1 = new Vehicle();
		vehicle1.setVehicleName("WaganoR");

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVehicleName("Tiago");

		userDetails.getVehicle().add(vehicle1);
		userDetails.getVehicle().add(vehicle2);

		vehicle1.getUser().add(userDetails);
		vehicle2.getUser().add(userDetails);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(userDetails);

		session.save(vehicle1);

		session.save(vehicle2);

		session.getTransaction().commit();

		session.close();

	}

	public static void main(String[] args) {
		BookManager manager = new BookManager();
		manager.setup();
		manager.create();

		manager.exit();
	}

}