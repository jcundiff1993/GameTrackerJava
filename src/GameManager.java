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
    //Launches the file that the contents of the GameTracker to.
    //If the file does not exist, it creates a file using a filepath variable that is declared above.
    // The variable filepath allows for ease of updating in future updates.
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
    //Loads files found in the GameTracker text file. If the file is not found, it returns an error.
    //The method reads each line from the tracker, skipping blank lines, and formatting each line to be stored
    // within the arraylist.
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
    //Ran whenever content has been successfully validated and added to the arraylist. Once completed, this method
    // formats each entry to the correct csv format to be stored to the GameTrackerContent.txt file.
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
    //This method adds games to the arraylist. It first confirms if the game already exists on that platform. If so,
    // it returns false indicating that the game was not added, and the entry is not added to the arraylist.
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
    //Searches for a game in the arraylist based on the game and platform (to prevent multiple entries returning
    // with games on different platforms), and if the game is not found, it returns false and provides an error. Otherwise
    // it displays the game's information.
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
    //Searches for a game in the arraylist based on the game's ID, and if the game is not found, it returns false and
    // provides an error. Otherwise, it displays the game's information.
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
    //Filters game by the indicated platform. If the game meets the criteria, it adds it to an arraylist which
    // is displayed once all games have been checked. If there are no games that meet that entry, it returns false
    // and advises there are no entries that meet the criteria.
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
    //After the game has confirmed to be found via search for game method, this method, passes through the game title,
    // the platform of the game, and the new genre that should be assigned to the game. While false should never be reached
    // if an error occurs, it will output an error message with the line for the code.
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
        System.out.println("Error point reached: GameManager.UpdateGenre");
        return false;
    }
    //After the game has confirmed to be found via search for game method, this method passes through the game title,
    // the platform of the game, and the new score that should be assigned to the game. While false should never be reached
    // if an error occurs, it will output an error message with the line for the code.
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
        System.out.println("Error point reached: GameManager.UpdateScore");
        return false;
    }
    //After the game has confirmed to be found via search for game method, this method passes through the game title,
    // the platform of the game, and the new Currently Playing status that should be assigned to the game. While false should never be reached
    // if an error occurs, it will output an error message with the line for the code.
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
        System.out.println("Error point reached: GameManager.UpdateCurrentlyPlaying");
        return false;
    }
    //Filters game by the indicated genre. If the game meets the criteria, it adds it to an arraylist which
    // is displayed once all games have been checked. If there are no games that meet that entry, it returns false
    // and advises there are no entries that meet the criteria.
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
    //Filters game by if the game is set to Currently Playing status of true. If the game meets the criteria, it adds it
    // to an arraylist which
    // is displayed once all games have been checked. If there are no games that meet that entry, it returns false
    // and advises there are no entries that meet the criteria.
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
    //Filters game by the indicated score. If the game meets the criteria, it adds it to an arraylist which
    // is displayed once all games have been checked. If there are no games that meet that entry, it returns false
    // and advises there are no entries that meet the criteria.
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
    //Searches for the game's title and platform indicated. If it is found, it removes the game and returns true.
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
    //Used to check in the arraylist is empty. Returns true if there are no entries, and false if entries exist.
    public boolean checkIfEmpty()
    {
        if(games.isEmpty())
        {
            return true;
        }
        return false;
    }
}