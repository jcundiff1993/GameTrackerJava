import java.util.Scanner;

public class Main {
    private static GameManager gameManager = new GameManager();
    private static ConsoleIO consoleIO = new ConsoleIO();
    private static Scanner scanner = new Scanner(System.in);
//    static boolean checkIfGameTrackerIsEmpty()
//    {
//        if(gameManager.checkIfEmpty())
//        {
//            System.out.println("There are currently no Games.\n");
//            System.out.println("If you want to add games, please choose option 1.");
//            return true;
//        }
//        return false;
//    }
    public static void main(String[] args) {
        boolean inUse = true;
        while (inUse)
        {
            consoleIO.displayMenu();
            System.out.print("Enter an option: ");
            String option = scanner.nextLine();
            switch (option)
            {
                case "1":
                    Game game = consoleIO.promptGame();
                    Boolean wasAdded = gameManager.addGame(game, game.getPlatform());
                    if (!wasAdded)
                    {
                        System.out.println("Game was not added");
                    }
                    else
                    {
                        System.out.println("Game was successfully added");
                    }
                    break;
                case "2":
                    if(gameManager.checkIfEmpty())
                    {
                        System.out.println("There are currently no Games.\n");
                        System.out.println("If you want to add games, please choose option 1.");
                    }
                    else
                    {
                        int choice = consoleIO.PromptInt("Search by Title = 1, or Game ID = 2?", 1,2);
                        if(choice == 1)
                        {
                            String title = consoleIO.PromptString("Enter Title: ", true).toUpperCase();
                            Boolean wasFoundWithTitle = gameManager.searchGame(title);
                            if(!wasFoundWithTitle)
                            {
                                System.out.println("Game not found.");
                            }
                        }
                        if(choice == 2)
                        {
                            int id = consoleIO.PromptInt("Enter the Game's Id: ",1,999999);
                            Boolean wasFoundWithId = gameManager.searchGame(id);
                            if(!wasFoundWithId)
                            {
                                System.out.println("Game not found.");
                            }
                        }
                    }
                    break;
                case "3":
                        String choice = consoleIO.PromptString("Filer by: Platform, Genre, Currently Playing" +
                                "or Score", true).toUpperCase();
                        switch(choice)
                        {
                            case "PLATFORM":
                                String platform = consoleIO.PromptString("Platform: ", true).toUpperCase();
                                Boolean wasFoundWithPlatform = gameManager.filterGamesByPlatform(platform);
                                break;
                            case "GENRE":
                                String genre = consoleIO.PromptString("Genre: ", true).toUpperCase();
                                Boolean wasFoundWithGenre = gameManager.filterGamesByGenre(genre);
                                break;
                            case "CURRENTLY PLAYING":
                                Boolean wasFoundByCurrentlyPlaying = gameManager.filterGamesByCurrentlyPlaying("true");
                                break;
                            case "SCORE":
                                int score = consoleIO.PromptInt("Score: ", 1, 10);
                                Boolean wasFoundByScore = gameManager.filterGamesByScore(score);
                                break;
                        }

                    break;
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
                        Boolean wasRemoved = gameManager.removeGame(gameToRemove, platformForGameToRemove);
                        if (!wasRemoved)
                        {
                            System.out.println("Game was not removed");
                            System.out.println("Please check your entry, and try again.");
                        }
                        else
                        {
                            System.out.println("Game was successfully removed");
                        }
                    }
                    break;
                case "5":
                    break;
                case "6":
                    System.out.print("Exiting GameTracker. Have a nice day!");
                    inUse = false;
                    break;
                case "7":
                    gameManager.displayAllGames();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    System.out.println("Enter any key to continue:");
                    scanner.nextLine();
            }
        }
    }
}
