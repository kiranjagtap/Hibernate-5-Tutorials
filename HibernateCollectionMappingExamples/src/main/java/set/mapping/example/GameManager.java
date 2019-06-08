package set.mapping.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import set.mapping.example.GameManager;

public class GameManager {

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
		SportsPlayer sportsPlayer = new SportsPlayer();
		sportsPlayer.setName("Sachin");

		Set<GameScore> score = new HashSet<>();

		score.add(new GameScore(56, "Cricket"));
		score.add(new GameScore(78, "Tenis"));

		sportsPlayer.setScores(score);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(sportsPlayer);
		session.getTransaction().commit();

		session.close();

	}

	protected void read() {
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from SportsPlayer");
		List<SportsPlayer> list = query.list();

		Iterator<SportsPlayer> itr = list.iterator();
		while (itr.hasNext()) {
			SportsPlayer sportsPlayer = itr.next();

			System.out.println("--------------------------------------");
			System.out.println("Game Player Name: " + sportsPlayer.getName());

			Set<GameScore> scores = sportsPlayer.getScores();
			Iterator<GameScore> itr2 = scores.iterator();
			System.out.println("--------------------------------------");
			while (itr2.hasNext()) {
				GameScore gameScore = itr2.next();
				System.out.println(gameScore.getSport() + " - " + gameScore.getScore());
			}
		}

		session.close();
		System.out.println("success");
	}

	public static void main(String[] args) {
		GameManager manager = new GameManager();
		manager.setup();
		manager.create();
		manager.read();
		manager.exit();
	}

}
