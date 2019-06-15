package hibernate.criteria.api;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

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

		Criteria criteria = session.createCriteria(UserDetails.class);
		/*
		 * criteria.add(Restrictions.eq("name", "user-7")) .add(Restrictions.gt("id",
		 * 5));
		 */
		criteria.add(Restrictions.like("name", "%user-7%"))
				.add(Restrictions.between("id", 4, 8));
		List<UserDetails> userList = criteria.list();

		session.getTransaction().commit();
		session.close();
		userList.forEach(s -> System.out.println(s.getName()));

	}

	protected void projection() {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		Criteria criteria = session.createCriteria(UserDetails.class).addOrder(Order.desc("id"));

		List<UserDetails> userList = criteria.list();

		session.getTransaction().commit();
		session.close();
		userList.forEach(s -> System.out.println(s.getName()));

	}

	protected void projection2() {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		UserDetails userExample = new UserDetails();
		userExample.setId(5);
		userExample.setName("user-5");

		Example example = Example.create(userExample);

		Criteria criteria = session.createCriteria(UserDetails.class).add(example);

		List<UserDetails> userList = criteria.list();

		session.getTransaction().commit();
		session.close();
		userList.forEach(s -> System.out.println(s.getName()));

	}

	protected void projection3() {
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		UserDetails userExample = new UserDetails();
		// userExample.setId(5);
		userExample.setName("user-1%");

		Example example = Example.create(userExample).enableLike();

		Criteria criteria = session.createCriteria(UserDetails.class).add(example);

		List<UserDetails> userList = criteria.list();

		session.getTransaction().commit();
		session.close();
		userList.forEach(s -> System.out.println(s.getName()));

	}

	public static void main(String[] args) {
		BookManager manager = new BookManager();
		manager.setup();
		// manager.create();
		//manager.read();
		 //manager.projection();
		// manager.projection2();
		manager.projection3();

		manager.exit();
	}

}