import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class GameManager {
    private ArrayList<Game> games;

    public GameManager()
    {
        games = new ArrayList<>();
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
