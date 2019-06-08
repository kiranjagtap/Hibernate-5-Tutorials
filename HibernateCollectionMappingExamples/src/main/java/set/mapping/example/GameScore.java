package set.mapping.example;

public class GameScore {

	int scoreId;
	int score;
	String sport;

	public GameScore() {};
	public GameScore(int score, String sport) {
		super();
		this.score = score;
		this.sport = sport;
	}

	public int getScoreId() {
		return scoreId;
	}

	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

}
