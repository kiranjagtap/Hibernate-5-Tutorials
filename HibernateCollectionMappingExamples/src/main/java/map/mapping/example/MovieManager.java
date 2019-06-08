package map.mapping.example;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MovieManager {
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
		MovieMap movie = new MovieMap();
		movie.setMovie("3 Idiots");

		Map<String, String> persons = new HashMap<>();

		persons.put("Director", "R.K. Hirani");
		persons.put("Actor", "Amir Khan");
		persons.put("Producer", "V.V. Chpra");
		movie.setPersons(persons);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(movie);
		session.getTransaction().commit();

		session.close();

	}

	protected void read() {
		Session session = sessionFactory.openSession();
		session.close();
		System.out.println("success");
	}

	public static void main(String[] args) {
		MovieManager manager = new MovieManager();
		manager.setup();
		manager.create();
		manager.read();
		manager.exit();
	}

}
