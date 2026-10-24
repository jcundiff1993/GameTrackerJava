import java.util.Scanner;

public class ConsoleIO {
    Scanner scanner = new Scanner(System.in);
    public Boolean isNullOrWhiteSpace(String str)
    {
        return str == null || str.isBlank();
    }
    public int parseWithDefault(String value, int defaultValue)
    {
        try {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    public void Display (String message)
    {
    System.out.println(message);
    }
    public String PromptString (String message, Boolean isRequired)
    {
        System.out.print(message);
        String result = scanner.nextLine();
        while (isRequired && isNullOrWhiteSpace(result))
        {
        System.out.print("Enter a string: ");
        result = scanner.nextLine();
        }
        return result.strip();
    }
    public int PromptInt(String message, int min, int max)
    {
        int failedParseScore = -1;
        String input = PromptString(message, true).trim();
        int result = parseWithDefault(input, failedParseScore);

        while( result < min || result > max)
        {
            System.out.print("Enter a number between " + min + " and " + max + ": ");
            input = PromptString(message, true).strip();
            result = parseWithDefault(input, failedParseScore);
        }
        return result;
    }
//    public Boolean PromptBoolean (String message)
//    {
//        Boolean validAnswer = false;
//        String answer = "";
//        do
//        {
//            answer = PromptString(message + " Y = Yes, N = No ", true).toLowerCase();
//            switch (answer)
//            {
//                case "y":
//                    validAnswer = true;
//                case "n":
//                    validAnswer = true;
//                    break;
//                default:
//                        Display("Invalid input. Please try again.");
//                        break;
//            }
//
//        }
//        while (!validAnswer);
//
//    }
    public void displayMenu()
    {
        System.out.println("\n******************************************");
        System.out.println("Welcome to Game Tracker");
        System.out.println("Please select an option from the menu:");
        System.out.println("1. Add Game");
        System.out.println("2. Search for a Game");
        System.out.println("3. Filter Games");
        System.out.println("4. Remove a Game");
        System.out.println("5. Edit a Game");
        System.out.println("6. Close Program");
        System.out.println("******************************************");
        System.out.print("What would you like to do?: ");
    }

    public Game promptGame()
    {
        int gameId = 0;
        String name = PromptString("What is the name of the game?", true).toUpperCase();
        String platform = PromptString("What is the platform of the game?", true).toUpperCase();
        String genre = PromptString("What is the genre of the game?", true).toUpperCase();
        Boolean currentlyPlaying = false;
        Boolean validPlayStatus = false;
        do
        {
            String checkIfPlaying = PromptString("Are you currently playing this game? Y = Yes, N = No ",true).toUpperCase();
            switch (checkIfPlaying)
            {
                case "Y":
                    currentlyPlaying = true;
                    validPlayStatus = true;
                    break;
                case "N":
                    currentlyPlaying = false;
                    validPlayStatus = true;
                    break;
                default:
                    Display("Please enter a valid option.");

            }
        }while(!validPlayStatus);
        int score = PromptInt("What is your score for the game from 0-10?", 0, 10);
        Game game = new Game(gameId,name, platform, genre, currentlyPlaying, score);
        return game;
    }

}
