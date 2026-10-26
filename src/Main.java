import java.util.Scanner;

public class Main {
    private static GameManager gameManager = new GameManager();
    private static ConsoleIO consoleIO = new ConsoleIO();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args)
    {
        //Launches the txt file that stores the database files.
        //If there is no txt file, it creates one.
        gameManager.launchFile();
        gameManager.loadFromFile();
        boolean inUse = true;
        while (inUse)
        {
            //Calls the consoleio class to display the menu.
            consoleIO.displayMenu();
            String option = consoleIO.PromptString("Enter an option: ", true);
            switch (option)
            {
                case "1":
                    Game game = consoleIO.promptGame();
                    //Calls the gameManager to attempt to add a game. If the game is added successfully,
                    // it will return an appropriate response and update the txt file.
                    boolean wasAdded = gameManager.addGame(game, game.getPlatform());
                    if (!wasAdded)
                    {
                        System.out.println("Game was not added");
                    }
                    else
                    {
                        System.out.println("Game was successfully added");
                        gameManager.commitToFile();
                    }
                    break;
                case "2":
                    //The search for a game first checks if the database has any files within.
                    if(gameManager.checkIfEmpty())
                    {
                        System.out.println("There are currently no Games.\n");
                        System.out.println("Please add games first.");
                    }
                    else
                    {
                        int choice = consoleIO.PromptInt("Search by Title = 1, or Game ID = 2?", 1,2);
                        if(choice == 1)
                        {
                            String title = consoleIO.PromptString("Enter Title: ", true).toUpperCase();
                            String platform = consoleIO.PromptString("Enter Platform: ", true).toUpperCase();
                            boolean wasFoundWithTitle = gameManager.searchGame(title,  platform);
                            if(!wasFoundWithTitle)
                            {
                                System.out.println("Game not found.");
                            }
                        }
                        if(choice == 2)
                        {
                            int id = consoleIO.PromptInt("Enter the Game's Id: ",1,999999);
                            boolean wasFoundWithId = gameManager.searchGame(id);
                            if(!wasFoundWithId)
                            {
                                System.out.println("Game not found.");
                            }
                        }
                    }
                    break;
                //Allows user to filter games based on their inteded filter (platform, genre, currently playing, or score.
                case "3":
                    if(gameManager.checkIfEmpty())
                    {
                        System.out.println("There are currently no Games.\n");
                        System.out.println("Please add games first.");
                    }
                    else {
                        boolean isValidFilter = false;
                        while (!isValidFilter) {
                            String choice = consoleIO.PromptString("Filer by: Platform, Genre, Currently Playing" +
                                    " or Score: ", true).toUpperCase();
                            switch (choice) {
                                case "PLATFORM":
                                    String platform = consoleIO.PromptString("Platform: ", true).toUpperCase();
                                    boolean wasFoundWithPlatform = gameManager.filterGamesByPlatform(platform);
                                    isValidFilter = true;
                                    break;
                                case "GENRE":
                                    String genre = consoleIO.PromptString("Genre: ", true).toUpperCase();
                                    boolean wasFoundWithGenre = gameManager.filterGamesByGenre(genre);
                                    isValidFilter = true;
                                    break;
                                case "CURRENTLY PLAYING":
                                    boolean wasFoundByCurrentlyPlaying = gameManager.filterGamesByCurrentlyPlaying();
                                    isValidFilter = true;
                                    break;
                                case "SCORE":
                                    int score = consoleIO.PromptInt("Score: ", 1, 10);
                                    boolean wasFoundByScore = gameManager.filterGamesByScore(score);
                                    isValidFilter = true;
                                    break;
                                default:
                                    System.out.println("Invalid input.");
                            }
                        }
                    }
                    break;
                //Allows users to remove titles from the GameTracker database. It confirms that games exist within the
                // database, and then prompts the user to enter the game and platform for the title; to prevent accidental
                // deletion of games with the same name on different platforms.
                case "4":
                    if(gameManager.checkIfEmpty())
                    {
                        System.out.println("There are currently no Games.\n");
                        System.out.println("If you want to add games, please choose option 1.");
                    }
                    else
                    {
                        String gameToRemove = consoleIO.PromptString("What game do you want to remove?",true).toUpperCase();
                        String platformForGameToRemove = consoleIO.PromptString("What is the console?",true).toUpperCase();
                        boolean wasRemoved = gameManager.removeGame(gameToRemove, platformForGameToRemove);
                        if (!wasRemoved)
                        {
                            System.out.println("Game was not removed");
                            System.out.println("Please check your entry, and try again.");
                        }
                        else
                        {
                            System.out.println("Game was successfully removed");
                            gameManager.commitToFile();
                        }
                    }
                    break;
                //Allows users to edit details about title's within the GameTracker database. It confirms that games exist within the
                // database, and then prompts the user to enter the game and platform for the title; to prevent accidental
                // editing of unintended games. It allows users to update the genre, currently playing status, or score
                // as updating the title and platform could create an unintended glitch where users could add duplicate
                // entries to the database.
                case "5":
                    if(gameManager.checkIfEmpty())
                    {
                        System.out.println("There are currently no Games.\n");
                        System.out.println("If you want to add games, please choose option 1.");
                    }
                    else
                    {
                        String gameToEdit = consoleIO.PromptString("What game do you want to Edit?",true).toUpperCase();
                        String platformForGameToEdit = consoleIO.PromptString("What is the console?",true).toUpperCase();
                        boolean wasFoundWithTitle = gameManager.searchGame(gameToEdit, platformForGameToEdit);
                        if(!wasFoundWithTitle)
                        {
                            System.out.println("Game not found.");
                        }
                        if(wasFoundWithTitle)
                        {
                            boolean validChoice = false;
                            do {
                                String choice = consoleIO.PromptString("Do you want to update the Genre, Currently Playing Status " +
                                        "or Score? (Type return to go to the Main Menu)", true).toUpperCase();
                                switch (choice) {
                                    case "GENRE":
                                        String genre = consoleIO.PromptString("Genre: ", true).toUpperCase();
                                        boolean wasGenreUpdatedSuccessfully = gameManager.updateGenre(gameToEdit, platformForGameToEdit, genre);
                                        if(wasGenreUpdatedSuccessfully)
                                        {
                                            consoleIO.Display(gameToEdit + " has been updated with the new genre: " + genre);
                                            gameManager.commitToFile();
                                        }
                                        else
                                        {
                                            consoleIO.Display("Something went wrong. Please try again.");
                                        }
                                        validChoice = true;
                                        break;
                                    case "CURRENTLY PLAYING":
                                        String currentlyPlayingStatusEntry = consoleIO.PromptString("Are you currently playing: " + gameToEdit + " on the " + platformForGameToEdit + " Yes, or No.", true).toUpperCase();
                                        boolean wasCurrentlyPlayingStatusUpdated = gameManager.updateCurrentlyPlaying(gameToEdit,  platformForGameToEdit, currentlyPlayingStatusEntry);
                                        if(wasCurrentlyPlayingStatusUpdated)
                                        {
                                            consoleIO.Display(gameToEdit + "'s currently playing status has been updated.");
                                            gameManager.commitToFile();
                                        }
                                        else
                                        {
                                            consoleIO.Display("Something went wrong. Please try again.");
                                        }
                                        validChoice = true;
                                        break;
                                    case "SCORE":
                                        int score = consoleIO.PromptInt("What is the new score for " + gameToEdit + " on the " + platformForGameToEdit + " : ", 1, 10);
                                        boolean wasScoreUpdatedSuccessfully = gameManager.updateScore(gameToEdit, platformForGameToEdit, score);
                                        if(wasScoreUpdatedSuccessfully)
                                        {
                                            consoleIO.Display(gameToEdit + "'s score was successfully updated.");
                                            gameManager.commitToFile();
                                        }
                                        else
                                        {
                                            consoleIO.Display("Something went wrong. Please try again.");
                                        }
                                        validChoice = true;
                                        break;
                                    case "RETURN":
                                        consoleIO.Display("Returning to the main menu.");
                                        validChoice = true;
                                        break;
                                    default:
                                        System.out.println("Invalid input.");
                                }
                            }while(!validChoice);

                        }
                    }
                    break;
                //Gracefully exits the game tracker.
                case "6":
                    System.out.print("Exiting GameTracker. Have a nice day!");
                    inUse = false;
                    break;
                //Advises the user entered an invalid entry.
                default:
                    System.out.println("Invalid option. Please try again.");
                    System.out.println("Enter any key to continue:");
                    scanner.nextLine();
            }
        }
    }
}
