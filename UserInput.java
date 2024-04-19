import java.util.Scanner;;

/** 
* Handles user input
*/
public class UserInput {
    private static final Scanner scan = new Scanner(System.in);

    /**
     * Reads and returns a string input from the user.
     * The method uses a Scanner object to read the input from the console, 
     * converts it to lowercase and removes any leading or trailing whitespaces.
     *
     * @return the string input from the user
     */
    public static String getValue() {
        return scan.nextLine().toLowerCase().strip();
    }

    /**
     * Forces the user to hit enterfor the code to continue
     */
    public static void pressEnter() {
        System.out.println("Press enter to continue.");
        scan.nextLine();
    }

    /**
     * Closes this scanner.
     */
    public static void close() {
        scan.close();
    }
}
