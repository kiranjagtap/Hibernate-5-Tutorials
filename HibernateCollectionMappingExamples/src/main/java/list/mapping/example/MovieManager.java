package list.mapping.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MovieManager {
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
		Movie movie = new Movie();
		movie.setMovie("3 Idiots");

		List<Song> songs = new ArrayList();

		songs.add(new Song("All is well"));
		songs.add(new Song("Zubu dubi"));

		movie.setSongs(songs);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(movie);
		session.getTransaction().commit();

		session.close();

	}
	
	protected void read() {
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from Movie");
		List<Movie> list = query.list();

		Iterator<Movie> itr = list.iterator();
		while (itr.hasNext()) {
			Movie movie = itr.next();

			System.out.println("--------------------------------------");
			System.out.println("Move Name: " + movie.getMovie());

			List<Song> songs = movie.getSongs();
			Iterator<Song> itr2 = songs.iterator();
			System.out.println("--------------------------------------");
			while (itr2.hasNext()) {
				Song song = itr2.next();
				System.out.println(song.getSongId()+" - "+song.getLyrics());
			}
		}

		session.close();
		System.out.println("success");
	}

	public static void main(String[] args) {
		MovieManager manager = new MovieManager();
		manager.setup();
        manager.create();
		manager.read();
		manager.exit();
	}

}
