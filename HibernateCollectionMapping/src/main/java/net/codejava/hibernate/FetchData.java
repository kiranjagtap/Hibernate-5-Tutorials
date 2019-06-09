package net.codejava.hibernate;

import javax.persistence.TypedQuery;
import java.util.*;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class FetchData {
	protected SessionFactory sessionFactory;

	public static void main(String[] args) {
	FetchData fetch = new FetchData();
		fetch.setup();
		fetch.create();
		fetch.exit();
	}

	protected void create() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("Spring is framework");
		list1.add("Spring is rest framework");

		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("Hibernate is ORM framework");
		list2.add("Hibernate not require server");

		Question question1 = new Question();
		question1.setQname("What is Spring?");
		question1.setAnswers(list1);

		Question question2 = new Question();
		question2.setQname("What is Hibernate?");
		question2.setAnswers(list2);

		session.persist(question1);
		session.persist(question2);

		session.getTransaction().commit();

		session.close();
		System.out.println("success");
	}

	protected void read() {
		Session session = sessionFactory.openSession();

		
		session.close();
	}

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
}