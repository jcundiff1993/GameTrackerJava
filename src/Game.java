//Creation of the Game class that is utilized to add entries of users into the GameTracker
public class Game {
    private int gameID;
    private String title;
    private String platform;
    private String genre;
    private Boolean currentlyPlaying;
    private int score;

    public Game(int gameID, String title, String platform, String genre, Boolean currentlyPlaying, int score)
    {
        this.gameID = gameID;
        this.title = title;
        this.platform = platform;
        this.genre = genre;
        this.currentlyPlaying = currentlyPlaying;
        this.score = score;
    }
    public int getGameID() {
        return gameID;
    }
    public String getTitle() {
        return title;
    }
    public String getPlatform() {
        return platform;
    }
    public String getGenre() {
        return genre;
    }
    public Boolean getCurrentlyPlaying() {
        return currentlyPlaying;
    }
    public int getScore() {
        return score;
    }
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setCurrentlyPlaying(Boolean currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }
    public void setScore(int score) {
        this.score = score;
    }
    @Override
    public String toString() {
        return "Game ID: " + getGameID() + " | Title: " + getTitle() + " | Platform: " + getPlatform() +  " | Genre: " + getGenre() + " | Currently Playing: " + getCurrentlyPlaying() + " | Score: " + getScore();
    }

}
