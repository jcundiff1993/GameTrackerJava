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
        File file = new File(filePath);
        if(!file.exists())
        {
            try (FileWriter writer = new FileWriter(filePath))
            {
                writer.write("Game Tracker Content");
                writer.write(System.lineSeparator());
            } catch (IOException e)
            {
                System.out.println("Error creating game tracker file: " + e.getMessage());
            }
        }
    }
    public void loadFromFile()
    {
        File file = new File(filePath);
        if(!file.exists())
        {
            System.out.println("Error: GameTracker File does not exist");
            return;
        }

        try (Scanner scanner = new Scanner(file))
        {
            if(scanner.hasNextLine())
            {
                scanner.nextLine();
            }
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                if(line.isBlank())
                {
                    continue;
                }
                String[] fields = line.split(",", -1);
                if(fields.length != 6)
                {
                    System.out.println("Invalid entry in gameTracker file: " + line);
                    continue;
                }
                try
                {
                    int gameId = Integer.parseInt(fields[0].trim());
                    String title = fields[1].trim();
                    String platform = fields[2].trim();
                    String genre = fields[3].trim();
                    boolean currentlyPlaying = Boolean.parseBoolean(fields[4].trim());
                    int score = Integer.parseInt(fields[5].trim());

                    Game game = new Game(gameId, title, platform, genre, currentlyPlaying, score);
                    games.add(game);
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Invalid entry in gameTracker file: " + line);
                }
            }
            System.out.println("Loaded game tracker file.");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error: GameTracker File does not exist");
        }
    }
    public void commitToFile()
    {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Game Tracker Content");
            writer.write(System.lineSeparator());
            for (Game game : games) {

                String csvEntry =
                        game.getGameID() + "," +
                                game.getTitle() + "," +
                                game.getPlatform() + "," +
                                game.getGenre() + "," +
                                game.getCurrentlyPlaying() + "," +
                                game.getScore();

                writer.write(csvEntry);
                writer.write(System.lineSeparator());
            }

            System.out.println("Game tracker data saved successfully.");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
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
    public boolean searchGame(String title, String platform)
    {
        for(Game storedGame : games)
        {
            if(storedGame.getTitle().equals(title) && storedGame.getPlatform().equals(platform))
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
    public boolean updateGenre(String gameToEdit, String platformForGameToEdit, String genre)
    {
        for(Game storedGame : games)
        {
            if(storedGame.getTitle().equals(gameToEdit) && storedGame.getPlatform().equals(platformForGameToEdit))
            {
                storedGame.setGenre(genre);
                return true;
            }
        }
        return false;
    }
    public boolean updateScore(String gameToEdit, String platformForGameToEdit, int newScore)
    {
        for(Game storedGame : games)
        {
            if(storedGame.getTitle().equals(gameToEdit) && storedGame.getPlatform().equals(platformForGameToEdit))
            {
                storedGame.setScore(newScore);
                return true;
            }
        }
        return false;
    }
    public boolean updateCurrentlyPlaying(String gameToEdit, String platformForGameToEdit, String currentlyPlayingStatusEntry)
    {
        for(Game storedGame : games)
        {
            if(storedGame.getTitle().equals(gameToEdit) && storedGame.getPlatform().equals(platformForGameToEdit))
            {

                switch(currentlyPlayingStatusEntry)
                {
                    case "YES":
                        storedGame.setCurrentlyPlaying(true);
                        return true;
                    case "NO":
                        storedGame.setCurrentlyPlaying(false);
                        return true;
                    default:
                        System.out.println("Invalid entry");
                        System.out.println("Please check your entry, and try again.");
                        return false;
                }
            }
        }
        return false;
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
    public boolean editGame(String title,String platform)
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