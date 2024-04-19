
/** 
* The class that the user 
*/
public class VecCalcuInterface {


    /**
     * Prints out the instruction on how to use the vector calculator
     */
    private static void VectorCalcuInstruction() {
        System.out.println("\n\n\n\n=======Vector Calculator=======");
        System.out.println("\n\nInstructions:");
        System.out.println("\nTo make a Vector use square bracket like:");
        System.out.println("For 3D Vector: [5, 3, 1], 2D: [3, 4], 1D: [2]");
        System.out.println("\nTo do scalar multiplication put a number before the Vector: 5[6, 4]");
        System.out.println("Brackets can also be used for scalar multiplication: 5([6, 4] + [4, 4])");
        System.out.println("\nFor operations put the symbol between the Vectors: 5[6, 4] + 5[4, 4] - 6[5, 4]");
        System.out.println("For Addition use +, Subtraction use -, Dot product use *, Cross product use x");
        System.out.println("The order of operations are: scalar multiplication, Cross, Dot, then Addition or Subtraction (which ever comes first)");
        System.out.println("\nWARNING: Can't do operations on Vectors with different dimensions");
        System.out.println("Like: 5[6, 4] + 5[4, 4, 6]");
        System.out.println("\nWARNING: Can't do operations between Vectors and scalars:");
        System.out.println("Like: 5[6, 4, 6] * 5[4, 4, 6] + 3[5, 6, 6]");
        System.out.println("\nTo avoid this use brackets like: 5[6, 4, 6] * (5[4, 4, 6] + 3[5, 6, 6])");
        System.out.println("\nWARNING: Don't use decimals, use fractions:");
        System.out.println("Improper: 7/2[-30/4, 4], Mixed: 3 1/2[-7 1/2, 4]");
        System.out.println("\nType in Exit to leave program");
    }

    /**
     * Runs the vector calculator program by repeatedly taking user input, calculating the result, and printing it.
     */
    public static void runVecCalcu() {
        String inputVec = "";

        VectorCalcuInstruction();

        while (!inputVec.equals("exit")) {
            System.out.println("\n\nEnter Vector to be Calculated:");
            inputVec = UserInput.getValue();

            if (!inputVec.equals("exit")) {
                try {
                    String calculatedVec = VectorCalculator.calculate(inputVec);
                    System.out.println("\nCalculated Vector: " + calculatedVec);
                } catch (IllegalAccessException e) {
                    System.out.println("\nError: " + e.getMessage());
                }
            }
        }
    }
}
