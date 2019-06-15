package hibernate.named.queries;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

	protected void read() {
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		Query query1 = session.getNamedQuery("userDetails.byId");
		query1.setParameter(0, 2);
		
		Query namedNativequery = session.getNamedQuery("userDetails.byName");
		namedNativequery.setString(0,"user-2");
		
		List<UserDetails> userList = namedNativequery.list();
		
		
		session.getTransaction().commit();
		session.close();
		userList.forEach(s->System.out.println(s.getName()));
		
	}
	public static void main(String[] args) {
		BookManager manager = new BookManager();
		manager.setup();
	//	manager.create();
		manager.read();
		manager.exit();
	}

}