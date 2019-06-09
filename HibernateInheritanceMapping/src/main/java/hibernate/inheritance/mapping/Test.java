package hibernate.inheritance.mapping;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {
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

		Employee emp = new Employee();
		emp.setName("Amita Sharma");

		SalesEmployee salesEmployee = new SalesEmployee();
		salesEmployee.setHrs(8);
		salesEmployee.setRates(1000);

		WageEmployee wageEmployee = new WageEmployee();
		wageEmployee.setCommission(50.5);
		wageEmployee.setSales(45);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.persist(emp);
		session.persist(salesEmployee);
		session.persist(wageEmployee);
		session.getTransaction().commit();

		session.close();

	}

	public static void main(String[] args) {
		Test test = new Test();
		test.setup();
		test.create();
		test.exit();
	}

}
