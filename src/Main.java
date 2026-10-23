import java.util.Scanner;

public class Main {
    private static GameManager gameManager = new GameManager();
    private static ConsoleIO consoleIO = new ConsoleIO();
    private static Scanner scanner = new Scanner(System.in);

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
                    break;
                case "3":
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
