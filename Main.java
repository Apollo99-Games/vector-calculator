public class Main {

    /**
     * Prints the intro options to the user
     */
    private static void printIntro() {
        System.out.println("\n\n\nWelcome to Vector calculator!");
        System.out.println("Type in 1 to use the Vector calculator.");
        System.out.println("Type in 2 to use the quiz.");
        System.out.println("Type in 3 to exit.");
    }

    /**
     * Runs the main loop of the program, which displays an introduction menu and
     * prompts the user for input.
     */
    private static void run() {
        String userAns = "";
        
        printIntro();

        while (!userAns.equals("3")) {
            System.out.println("\nEnter: ");
            userAns = UserInput.getValue();

            if (userAns.equals("1")) {
                VecCalcuInterface.runVecCalcu();
                printIntro();
            }
            else if (userAns.equals("2")) {
                Quiz.runQuiz();
                printIntro();
            }
            else if (!userAns.equals("3")) {
                System.out.println("Wronge Input. Try Again.");
            }
        }
        UserInput.close();
    }

    public static void main(String[] args) {
        run();
    }
}
