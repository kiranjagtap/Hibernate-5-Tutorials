package net.codejava.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * BookManager.java
 * A Hibernate hello world program
 * @author www.codejava.net
 *
 */
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
		Book book1 = new Book();
		book1.setTitle("Effective Structs");
		book1.setAuthor("Vidya");
		book1.setPrice(50.60f);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		
		System.out.println("---------------------"+book1.getId());
		session.save(book1);

		session.getTransaction().commit();
		
		
		session.close();

	}

	protected void read() {
		Session session = sessionFactory.openSession();

		long bookId = 17;
		Book book = (Book) session.get(Book.class, bookId);

		System.out.println("Title: " + book.getTitle());
		System.out.println("Author: " + book.getAuthor());
		System.out.println("Price: " + book.getPrice());

		session.close();
	}

	protected void update() {
		Book book = new Book();
		book.setId(19);
		book.setTitle("Ultimate Movie");
		book.setAuthor("Avengers");
		book.setPrice(200.50f);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.update(book);

		session.getTransaction().commit();
		session.close();
	}

	protected void delete() {
		Book book = new Book();
		book.setId(20);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.delete(book);

		session.getTransaction().commit();
		session.close();
	}
	
	public static void main(String[] args) {
		BookManager manager = new BookManager();
		manager.setup();
		//manager.delete();
		manager.read();
		
		//manager.delete();
		
		manager.exit();
	}

}
