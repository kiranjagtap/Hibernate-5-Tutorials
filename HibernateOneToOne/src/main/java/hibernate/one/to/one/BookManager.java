package hibernate.one.to.one;

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

		Vehicle vehicle = new Vehicle();

		vehicle.setVehicleName("WaganoR");

		userDetails.setVehicle(vehicle);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(userDetails);

		session.save(vehicle);

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