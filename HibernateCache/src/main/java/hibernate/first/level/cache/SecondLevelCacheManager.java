package hibernate.first.level.cache;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cache.NoCacheRegionFactoryAvailableException;
import org.hibernate.cfg.Configuration;

public class SecondLevelCacheManager {
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
		Session session = sessionFactory.openSession();

		for (int i = 0; i < 10; i++) {
			session.beginTransaction();
			UserDetails userDetails = new UserDetails();
			userDetails.setName("user-" + i);
			session.save(userDetails);
			session.getTransaction().commit();
		}

		session.close();

	}

	protected void readUsingSecondLevelCache() {
		Session session1 = sessionFactory.openSession();

		session1.beginTransaction();

		UserDetails user1 = (UserDetails) session1.get(UserDetails.class,1);
		//user1.setName("user-1");
		UserDetails user2 = (UserDetails) session1.get(UserDetails.class,1);
			
		//session1
		session1.getTransaction().commit();
		session1.close();

		//session2
		Session session2 = sessionFactory.openSession();

		session2.beginTransaction();
		UserDetails user3 = (UserDetails) session2.get(UserDetails.class,1);
		
		session2.getTransaction().commit();
		session2.close();
	}

	public static void main(String[] args) {
		SecondLevelCacheManager manager = new SecondLevelCacheManager();
		manager.setup();
		// manager.create();
		manager.readUsingSecondLevelCache();
		manager.exit();
	}

}