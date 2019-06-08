package map.mapping.example;

import java.util.Map;

public class MovieMap {

	int movieId;
	String movie;
	Map persons;
	
	public MovieMap() {}
	public MovieMap(int movieId, String movie, Map persons) {
		super();
		this.movieId = movieId;
		this.movie = movie;
		this.persons = persons;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public Map getPersons() {
		return persons;
	}
	public void setPersons(Map persons) {
		this.persons = persons;
	}
	
	
}
