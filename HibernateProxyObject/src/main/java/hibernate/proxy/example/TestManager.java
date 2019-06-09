package hibernate.proxy.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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

		UserDetails userDetails = new UserDetails();
		userDetails.setUserName("First User");

		List<Address> setOfAddresses = new ArrayList<>();
		
		Address add1 = new Address();
		add1.setStreet("Laxmi Street 2");
		add1.setCity("Lohgaon");
		add1.setState("MH");
		add1.setCountry("India");

		Address add2 = new Address();
		add2.setStreet("Nigdi Street");
		add2.setCity("Aundh");
		add2.setState("MH");
		add2.setCountry("India");

		setOfAddresses.add(add1);
		setOfAddresses.add(add2);
		userDetails.setListOfAddresses(setOfAddresses);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(userDetails);

		session.getTransaction().commit();

		session.close();

	}

	protected void read() {
		UserDetails user=null;
		Session session = sessionFactory.openSession();

		user = (UserDetails) session.get(UserDetails.class, 1);
		System.out.println("---------------------------------");
		System.out.println("User name- "+user.getUserName());
		
		session.close();
		//to test object is proxy object or not, test with closing session and remove fetchtype eager initialization
			
		System.out.println("---------------------------------");
		System.out.println("Address- ");
		Collection<Address> address = user.getListOfAddresses();
		Iterator<Address> addressItr= address.iterator();
		
		while(addressItr.hasNext()) {
			Address add = addressItr.next();
			System.out.println(add.getStreet());
			System.out.println(add.getCity());
			System.out.println(add.getState());
			System.out.println(add.getCountry());
		}
		
		session.close();
	}

	public static void main(String[] args) {
		TestManager manager = new TestManager();
		
		manager.setup();
		//manager.create();
		manager.read();
		manager.exit();
	}

}