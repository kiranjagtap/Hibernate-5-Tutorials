package hibernate.embeded.example;

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
			sessionFactory = new Configuration().configure().addAnnotatedClass(UserDetails.class).buildSessionFactory();
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
		userDetails.setUserName("Vikrant");

		Address add = new Address();
		add.setStreet("Laxmi Street");
		add.setCity("Pune");
		add.setState("MH");
		add.setCountry("India");

		userDetails.setAddress(add);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(userDetails);

		session.getTransaction().commit();

		session.close();

	}

	protected void read() {
		Session session = sessionFactory.openSession();

		session.close();
	}

	public static void main(String[] args) {
		BookManager manager = new BookManager();
		
		manager.setup();
		manager.create();
		
		manager.exit();
	}

}