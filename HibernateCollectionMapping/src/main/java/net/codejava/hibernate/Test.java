package net.codejava.hibernate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {

	public static void main(String[] args) {

		Test manager = new Test();
		manager.setup();
		// manager.delete();
		manager.create();

		System.out.println("---------Using before Java8-----------");
		manager.read();
		System.out.println("---------Using before Java8-----------");
		manager.readUsingJava8();

		// manager.delete();

		manager.exit();

	}

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
		Employee employee = new Employee();
	
		employee.setEmployeeName("Saurabh");
		Set<String> phoneNumbers = new HashSet<String>();
		phoneNumbers.add("11111111");
		phoneNumbers.add("22222222");
		phoneNumbers.add("33333333");
		employee.setPhoneNumbers(phoneNumbers);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(employee);

		session.getTransaction().commit();

		session.close();

	}

	protected void readUsingJava8() {
		Session session = sessionFactory.openSession();

		Stream<Employee> empStream = session.createQuery("SELECT h FROM Employee h WHERE id = 1", Employee.class)
				.stream();

		empStream.map(Employee::getEmployeeName).forEach(System.out::println);
		/*
		 * List<Employee> list = query.list();
		 * 
		 * Iterator<Employee> itr = list.iterator(); while (itr.hasNext()) { Employee
		 * emp = itr.next(); System.out.println("Employee Name: " +
		 * emp.getEmployeeName());
		 * 
		 * // printing answers Set<String> set = emp.getPhoneNumbers(); Iterator<String>
		 * itr2 = set.iterator(); while (itr2.hasNext()) {
		 * System.out.println(itr2.next()); }
		 */ }

	protected void read() {
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from Employee");
		List<Employee> list = query.list();

		Iterator<Employee> itr = list.iterator();
		while (itr.hasNext()) {
			Employee emp = itr.next();
			System.out.println("Employee Name: " + emp.getEmployeeName());

			// printing answers
			Set<String> set = emp.getPhoneNumbers();
			Iterator<String> itr2 = set.iterator();
			while (itr2.hasNext()) {
				System.out.println(itr2.next());
			}
		}

		session.close();
		System.out.println("success");
	}
}
