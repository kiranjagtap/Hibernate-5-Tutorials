package list.mapping.example;

public class Song {

	int songId;
	String lyrics;
	
	public Song() {}
	public Song(String lyrics) {
		this.lyrics = lyrics;
	}
	public int getSongId() {
		return songId;
	}
	public void setSongId(int songId) {
		this.songId = songId;
	}
	public String getLyrics() {
		return lyrics;
	}
	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
	
	
}
