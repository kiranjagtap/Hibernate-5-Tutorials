package net.codejava.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ClientTest {

	public static void main(String[] args) {
	    try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Check MySQL database version
			String sql = "select version()";
			long bookId = 1;
			Book book = (Book) session.get(Book.class, bookId);

			System.out.println("Title: " + book.getTitle());
			System.out.println("Author: " + book.getAuthor());
			System.out.println("Price: " + book.getPrice());
			
			String result = (String) session.createNativeQuery(sql).getSingleResult();
			System.out.println("MySql Database Version is:::");
			System.out.println(result);
			HibernateUtil.exit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	  }
}