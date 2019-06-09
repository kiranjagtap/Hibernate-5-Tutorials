package net.codejava.hibernate;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

/**
 * @author Kiran Jagtap
 */
public class HibernateUtil {
	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;

	static {

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

		// Apply database settings

		StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
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

	// Utility method to return SessionFactory
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// Utility method to close SessionFactory
	public static void exit() {
		sessionFactory.close();
	}
}