package hibernate.proxy.example;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * BookManager.java A Hibernate hello world program
 * 
 * @author www.codejava.net
 *
 */
public class TestManager {
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
		Session session = null;
		UserDetails userDetails = new UserDetails();
		userDetails.setUserName("Monil");

		// Transient State
		session = sessionFactory.openSession();
		session.beginTransaction();

		
		// Persistent state
		session.save(userDetails);
		
		userDetails.setUserName("Modi");
		
		session.getTransaction().commit();
		session.close();


		// Detaced State
/*		
		session = sessionFactory.openSession();
		session.beginTransaction();

		session.update(userDetails);

		session.getTransaction().commit();

		session.close();
*/
		
	}

	protected void read() {
		UserDetails user = null;
		Session session = sessionFactory.openSession();

		user = (UserDetails) session.get(UserDetails.class, 1);
		System.out.println("---------------------------------");
		System.out.println("User name- " + user.getUserName());

		session.close();
	}

	public static void main(String[] args) {
		TestManager manager = new TestManager();

		manager.setup();
		manager.create();
		// manager.read();
		manager.exit();
	}

}