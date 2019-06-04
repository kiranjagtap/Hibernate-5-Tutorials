package net.codejava.hibernate;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
/**
 * @author kishan Kumar
 */
public class HibernateUtil {
	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;
 
	static {
		// Creating StandardServiceRegistryBuilder 
		StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
		Configuration configuration = new Configuration();
		Properties properties = new Properties(); 
		// Hibernate settings which is equivalent to hibernate.cfg.xml's properties
		
		properties.put(Environment.URL, "jdbc:mysql://localhost:3306/bookstore?useSSL=false");
		properties.put(Environment.USER, "root");
		properties.put(Environment.PASS, "root");
		properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
		properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
		properties.put(Environment.SHOW_SQL, "true");

		configuration.setProperties(properties);
		configuration.addAnnotatedClass(Book.class);
		/*Map<String, String> dbSettings = new HashMap<>();
		dbSettings.put(Environment.URL, "jdbc:mysql://localhost:3306/bookstore?useSSL=false");
		dbSettings.put(Environment.USER, "root");
		dbSettings.put(Environment.PASS, "root");
		dbSettings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
		dbSettings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");*/
		//dbSettings.put(Environment, arg1)
		
		// Apply database settings
		registryBuilder.applySettings(configuration.getProperties());
		// Creating registry
		standardServiceRegistry = registryBuilder.build();
		// Creating MetadataSources
		MetadataSources sources = new MetadataSources(standardServiceRegistry);
		// Creating Metadata
		Metadata metadata = sources.getMetadataBuilder().build();
		// Creating SessionFactory
		sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);
	}
	//Utility method to return SessionFactory
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static void exit() {
		sessionFactory.close();
	}
}