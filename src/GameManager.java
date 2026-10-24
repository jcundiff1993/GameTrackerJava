import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class GameManager {
    private ArrayList<Game> games;
    private String filePath = "gameTrackerContent.txt";

    public GameManager()
    {
        games = new ArrayList<>();
    }
    public void launchFile()
    {
        try(FileWriter writer = new FileWriter(filePath))
        {
            writer.write("Game Tracker Content");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Could not locate file location");
        }
        catch(IOException e)
        {
            System.out.println("Error writing to file");
        }
    }
    public boolean addGame(Game game,String platform)
    {
        int goToNextEntryInArrayList = 1;
        for(Game storedGame : games)
        {
            if(storedGame.getTitle().equals(game.getTitle()) && storedGame.getPlatform().equals(platform))
            {
                System.out.println("This game already exists on this system");
                System.out.println("Please check your entry, and try again.");
                return false;
            }
        }
        int id = games.size();
        game.setGameID(id + goToNextEntryInArrayList);
        games.add(game);
        return true;
    }
    public boolean searchGame(String title)
    {
        for(Game storedGame : games)
        {
            if(storedGame.getTitle().equals(title))
            {
                System.out.println(storedGame.toString());
                return true;
            }
        }
        System.out.println("Game not found.");
        System.out.println("Please check your entry, and try again.");
        return false;
    }
    public boolean searchGame(int id)
    {
        for(Game storedGame : games)
        {
            if(storedGame.getGameID() == id)
            {
                System.out.println(storedGame.toString());
                return true;
            }
        }
        System.out.println("Game not found.");
        System.out.println("Please check your entry, and try again.");
        return false;
    }
    public boolean filterGamesByPlatform(String filter)
    {
        ArrayList<Game> filteredGames = new ArrayList<>();

        for(Game storedGame : games)
        {
            if(storedGame.getPlatform().equals(filter))
            {
                filteredGames.add(storedGame);
            }
        }
        if(filteredGames.size() == 0)
        {
            System.out.println("There are currently no games that meet that criteria.");
            return false;
        }
        for(Game game : filteredGames)
        {
            System.out.println(game.toString());
        }
        return true;
    }
    public boolean filterGamesByGenre(String filter)
    {
        ArrayList<Game> filteredGames = new ArrayList<>();

        for(Game storedGame : games)
        {
            if(storedGame.getGenre().equals(filter))
            {
                filteredGames.add(storedGame);
            }
        }
        if(filteredGames.size() == 0)
        {
            System.out.println("There are currently no games that meet that criteria.");
            return false;
        }
        for(Game game : filteredGames)
        {
            System.out.println(game.toString());
        }
        return true;
    }
    public boolean filterGamesByCurrentlyPlaying()
    {
        ArrayList<Game> filteredGames = new ArrayList<>();

        for(Game storedGame : games)
        {
            if(storedGame.getCurrentlyPlaying().equals(true))
            {
                filteredGames.add(storedGame);
            }
        }
        if(filteredGames.size() == 0)
        {
            System.out.println("You are not currently playing any games in your collection.");
            System.out.println("Make sure to add any new games, or start playing games from your collection.");
            return false;
        }
        for(Game game : filteredGames)
        {
            System.out.println(game.toString());
        }
        return true;
    }
    public boolean filterGamesByScore(int filter)
    {
        ArrayList<Game> filteredGames = new ArrayList<>();

        for(Game storedGame : games)
        {
            if(storedGame.getScore() == filter)
            {
                filteredGames.add(storedGame);
            }
        }
        if(filteredGames.size() == 0)
        {
            System.out.println("There are currently no games that meet that criteria.");
            return false;
        }
        for(Game game : filteredGames)
        {
            System.out.println(game.toString());
        }
        return true;
    }
    public boolean removeGame(String title,String platform)
    {
        for(Game storedGame : games)
        {
            if(storedGame.getTitle().equals(title) && storedGame.getPlatform().equals(platform))
                {
                    games.remove(storedGame);
                    return true;
                }
        }
        return false;
    }
    public void displayAllGames()
    {
        if(games.isEmpty())
        {
            System.out.println("Your game collection is currently empty!.");
            System.out.println("Add some games to your collection first.");
        }
        else
        {
            for(Game game : games)
                {
                    System.out.println(game);
                }
        }
    }
    public boolean checkIfEmpty()
    {
        if(games.isEmpty())
        {
            return true;
        }
        return false;
    }
}