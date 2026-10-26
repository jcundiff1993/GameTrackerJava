import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {
    private GameManager gameManager;
    @BeforeEach
    void setup()
    {
        gameManager = new GameManager();
    }
    @org.junit.jupiter.api.Test
    void addGame() throws Exception
    {
        Game game = new Game(0,"MINECRAFT","PC","OPEN WORLD",true,10);

        boolean result = gameManager.addGame(game,"PC");

        assertEquals(true,result);
    }
    @org.junit.jupiter.api.Test
    void addGameShouldFailForDuplicate() throws Exception
    {
        Game game = new Game(0,"MINECRAFT","PC","OPEN WORLD",true,10);
        Game game2 = new Game(1,"MINECRAFT","PC","OPEN WORLD",true,10);

        boolean result1 = gameManager.addGame(game,"PC");
        boolean result2 = gameManager.addGame(game,"PC");

        assertEquals(false,result2);
    }

    @org.junit.jupiter.api.Test
    void searchGameShouldPassWhenSearchingForGameByNameAndPlatform() throws Exception
    {
        Game game = new Game(0,"Minecraft","PC","Open World",true,10);

        boolean resultOfAddingGame = gameManager.addGame(game,"PC");

        boolean result = gameManager.searchGame("Minecraft", "PC");

        assertEquals(true,result);
    }
    @org.junit.jupiter.api.Test
    void searchGameShouldFailForEmptyGameByName() throws Exception
    {
        boolean result = gameManager.searchGame("Minecraft", "PC");

        assertEquals(false,result);
    }
    @org.junit.jupiter.api.Test
    void testSearchGameByIdShouldPassWhenGameIsInArrayList() throws Exception
    {
        Game game = new Game(0,"Minecraft","PC","Open World",true,10);

        boolean resultOfAddingGame = gameManager.addGame(game,"PC");

        boolean result = gameManager.searchGame(1);

        assertEquals(true,result);
    }
    @org.junit.jupiter.api.Test
    void testSearchGameByIdShouldFailForEmptyGameById() throws Exception
    {
        boolean result = gameManager.searchGame(1);

        assertEquals(false,result);
    }
    @org.junit.jupiter.api.Test
    void filterGamesByPlatformShouldPassWhenPlatformHasGames() throws Exception
    {
        Game game = new Game(0,"MINECRAFT","PC","OPEN WORLD",true,10);
        boolean addingGame = gameManager.addGame(game,"PC");
        boolean result = gameManager.filterGamesByPlatform("PC");

        assertEquals(true,result);
    }
    @org.junit.jupiter.api.Test
    void filterGamesByPlatformShouldFailWhenPlatformHasNoGames() throws Exception
    {
        Game game = new Game(0,"MINECRAFT","XBOX","OPEN WORLD",true,10);
        boolean addingGame = gameManager.addGame(game,"PC");
        boolean result = gameManager.filterGamesByPlatform("PC");

        assertEquals(false,result);
    }
    @org.junit.jupiter.api.Test
    void updateGenreShouldPassWhenUpdatingGenre() throws Exception
    {
        Game game = new Game(0,"MINECRAFT","PC","OPEN WORLD",true,10);
        String genre = "RPG";
        boolean addGameSuccessfully = gameManager.addGame(game,"PC");

        boolean result = gameManager.updateGenre("MINECRAFT", "PC", genre);
        assertEquals(true,result);
        assertEquals(game.getGenre(),genre);
    }

    @org.junit.jupiter.api.Test
    void updateScoreShouldSuccessfullyUpdateScore() throws Exception
    {
        Game game = new Game(0,"MINECRAFT","PC","OPEN WORLD",true,10);
        int score = 9;
        boolean addGameSuccessfully = gameManager.addGame(game,"PC");

        boolean result = gameManager.updateScore("MINECRAFT", "PC", score);
        assertEquals(true,result);
        assertEquals(game.getScore(),score);
    }
    @org.junit.jupiter.api.Test
    void updateScoreShouldFailToUpdateScoreWhenGameIsNotFound() throws Exception
    {
        Game game = new Game(0,"MINECRAFT","PC","OPEN WORLD",true,10);
        int score = 9;
        boolean addGameSuccessfully = gameManager.addGame(game,"PC");

        boolean result = gameManager.updateScore("Parappa The Rapper", "PS1", score);
        assertEquals(false,result);
    }
    @org.junit.jupiter.api.Test
    void updateCurrentlyPlayingToCurrentlyPlayingGame() throws Exception
    {
        Game game = new Game(0,"MINECRAFT","PC","OPEN WORLD",true,10);
        boolean addGameSuccessfully = gameManager.addGame(game,"PC");
        boolean updatedGameSuccessfully = gameManager.updateCurrentlyPlaying("MINECRAFT","PC", "NO");

        assertEquals(true,updatedGameSuccessfully);

    }
    @org.junit.jupiter.api.Test
    void updateCurrentlyPlayingToCurrentlyPlayingShouldFailWithInvalidEntry() throws Exception
    {
        Game game = new Game(0,"MINECRAFT","PC","OPEN WORLD",true,10);
        boolean addGameSuccessfully = gameManager.addGame(game,"PC");
        boolean updatedGameSuccessfully = gameManager.updateCurrentlyPlaying("MINECRAFT","PC", "I'm playing it!");

        assertEquals(false,updatedGameSuccessfully);

    }
    @org.junit.jupiter.api.Test
    void filterGamesByGenre()
    {
        Game game = new Game(0,"MINECRAFT","PC","OPEN WORLD",true,10);
        boolean addingGame = gameManager.addGame(game,"PC");

        boolean result = gameManager.filterGamesByGenre("OPEN WORLD");

        assertEquals(true,result);
    }
    @org.junit.jupiter.api.Test
    void filterGamesByGenreShouldFailWhenPlatformHasNoGames() throws Exception
    {
        Game game = new Game(0,"MINECRAFT","XBOX","OPEN WORLD",true,10);
        boolean addingGame = gameManager.addGame(game,"PC");
        boolean result = gameManager.filterGamesByGenre("RPG");

        assertEquals(false,result);
    }
    @org.junit.jupiter.api.Test
    void filterGamesByCurrentlyPlayingShouldPassWhenTheirAreGamesWithTheStatusOfCurrentlyPlaying() throws Exception
    {
        Game game = new Game(0,"MINECRAFT","PC","OPEN WORLD",true,10);
        boolean addingGame = gameManager.addGame(game,"PC");

        boolean result = gameManager.filterGamesByCurrentlyPlaying();

        assertEquals(true,result);
    }
    @org.junit.jupiter.api.Test
    void filterGamesByCurrentlyPlayingShouldFailWhenTheirAreNoGamesWithTheStatusOfCurrentlyPlaying() throws Exception
    {
        Game game = new Game(0,"MINECRAFT","PC","OPEN WORLD",false,10);
        boolean addingGame = gameManager.addGame(game,"PC");

        boolean result = gameManager.filterGamesByCurrentlyPlaying();

        assertEquals(false,result);
    }

    @org.junit.jupiter.api.Test
    void filterGamesByScore()
    {
        Game game = new Game(0,"MINECRAFT","PC","OPEN WORLD",true,10);
        boolean addingGame = gameManager.addGame(game,"PC");

        boolean result = gameManager.filterGamesByScore(10);

        assertEquals(true,result);
    }

    @org.junit.jupiter.api.Test
    void removeGameShouldPassRemovingAGame()
    {
        Game game = new Game(0,"Minecraft","PC","Open World",true,10);

        boolean resultOfAddingGame = gameManager.addGame(game,"PC");

        boolean result = gameManager.removeGame("Minecraft", "PC");
        assertEquals(true,result);
    }
    @org.junit.jupiter.api.Test
    void checkIfEmptyShouldSuccessfullyRecognizeEmptyArrayList()
    {
        boolean result = gameManager.checkIfEmpty();
        assertEquals(true,result);
    }
    @org.junit.jupiter.api.Test
    void checkIfEmptyShouldFailWithEntriesInDatabase()
    {
        Game game = new Game(0,"Minecraft","PC","Open World",true,10);

        boolean resultOfAddingGame = gameManager.addGame(game,"PC");

        boolean result = gameManager.checkIfEmpty();
        assertEquals(false,result);
    }
}