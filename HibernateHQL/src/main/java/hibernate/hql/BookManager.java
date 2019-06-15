package hibernate.hql;

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
		
		Query query = session.createQuery("from UserDetails");
		query.setFirstResult(5);
		query.setMaxResults(4);
		List<UserDetails> userList = query.list();
		
		
		session.getTransaction().commit();
		session.close();
		userList.forEach(s->System.out.println(s.getName()));
		
	}
	
	protected void sqlInjection() {
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();

		String minUserId = "5; or 1=1";
		Query query = session.createQuery("from UserDetails where id >" + minUserId);
		
		List<UserDetails> userList = query.list();
		
		
		session.getTransaction().commit();
		session.close();
		userList.forEach(s->System.out.println(s.getName()));
		
	}
	
	protected void parameterBinding() {
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();

		String minUserId = "5";
		Query query1 = session.createQuery("from UserDetails where id > ?");
		query1.setParameter(0, Integer.parseInt(minUserId));
		
		
		Query query2 = session.createQuery("from UserDetails where id > ? and name = ?");
		query2.setParameter(0, Integer.parseInt(minUserId));
		query2.setParameter(1, "user-7");
		
		Query query3 = session.createQuery("from UserDetails where id> :userId and name = :userName");
		
		query3.setParameter("userId", Integer.parseInt(minUserId));
		query3.setParameter("userName", "user-8");
		
		List<UserDetails> userList = query3.list();
		
		
		session.getTransaction().commit();
		session.close();
		userList.forEach(s->System.out.println(s.getName()));
		
	}
	public static void main(String[] args) {
		BookManager manager = new BookManager();
		manager.setup();
		//manager.create();
		//manager.read();
		//manager.sqlInjection();
		manager.parameterBinding();
		manager.exit();
	}

}