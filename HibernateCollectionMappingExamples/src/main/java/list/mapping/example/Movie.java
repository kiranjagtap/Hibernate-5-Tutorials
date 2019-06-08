package list.mapping.example;

import java.util.List;

public class Movie {

	int movieId;
	String movie;
	List<Song> songs;
	
	public Movie() {}
	public Movie(int movieId, String movie, List<Song> songs) {
		super();
		this.movieId = movieId;
		this.movie = movie;
		this.songs = songs;
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
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
	
	
}
