
/** 
* Generates and creates random quizes
*/
public class Quiz {

    private enum questionType {
        crossProduct,
        dotProduct,
        add,
        subtract,
        angle,
        complex
    }

    /**
     * Prints the insructions on how to use the quiz
     */
    private static void printInstructions() {
        System.out.println("\n\nType in the answer to the questions that will appear");
        System.out.println("\nIf final answer is a vector put it in vector format: [4, 3, 6]");
        System.out.println("\nIf final answer is a scalar don't put square brackets: -56");
        System.out.println("\nDon't use decimals, use fractions, for angles round to nearest the integer.");
        System.out.println("\nTo reveal answer type in Answer");
        System.out.println("\nTo leave type in Exit");
    }

    /**
     * Prints all the options for the quiz
     */
    private static void printIntro() {
        System.out.println("\n\n=====Welcome to Quiz=====");
        System.out.println("\n\nType in a number to pick a question to practise:");
        System.out.println("1. Cross Product");
        System.out.println("2. Dot Product");
        System.out.println("3. Subtract Vectors");
        System.out.println("4. Add Vectors");
        System.out.println("5. Find angle between Vectors");
        System.out.println("6. Mix of add, subtract, scalar multiple, and Cross Product");
        System.out.println("7. Exit");
    }

    /**
     * Generates a random vector-scalar equation in three dimensions.
     * 
     * @return a string representation of a random vector-scalar equation in three dimensions.
     */
    private static String randomVecScalar3D() {
        return Fraction.random().toString() + vector.random3D().toString();
    }

    /**
     * Generates a random vector-scalar equation in two dimensions.
     * 
     * @return a string representation of a random vector-scalar equation in two dimensions.
     */
    private static String randomVecScalar2D() {
        return Fraction.random().toString() + vector.random2D().toString();
    }

    /**
     * Selects and generates a random question based on the given questionType.
     *
     * @param type the type of the question to generate
     * @return a string representation of the generated question
     */
    private static String selectQuestion(questionType type) {
        switch (type) {
            case crossProduct:
                return generateOperationQuestion(type).replace("@", "x");
            case dotProduct:
                return generateOperationQuestion(type).replace("@", "*");
            case add:
                return generateOperationQuestion(type).replace("@", "+");
            case subtract:
                return generateOperationQuestion(type).replace("@", "-");
            case angle:
                return generateAngleQuestion();
            default:
                return generateComplexQuestion();
        }
    }

    /**
     * Generates a random question asking for the angle between two vectors.
     * The dimension of the vectors are 2D or 3D.
     *
     * @return a string representing the generated question.
     */
    private static String generateAngleQuestion() {
        int dimension = (int)(Math.random() * 2 + 1);
        if (dimension == 1) {
            return "Find angle between " + vector.random3D() + " and " + vector.random3D();
        }
        else {
            return "Find angle between " + vector.random2D() + " and " + vector.random2D();
        }
    }

    /**
     * Generates an operation question for the user based on the given question type.
     * Returns a string representing the operation between two randomly generated vectors.
     * 
     * @param type the type of question to generate
     * @return a string representing the operation between two randomly generated vectors
     */
    private static String generateOperationQuestion(questionType type) {
        int dimension = (int)(Math.random() * 2 + 1);
        if (dimension == 1 && type != questionType.crossProduct) {
            return vector.random2D() + " @ " + vector.random2D();
        }
        else {
            return vector.random3D() + " @ " + vector.random3D();
        }
    }

    /**
     * Generates an operation question which has scalar multiplying, addition, subtraction, and cross product.
     * 
     * @return a string representing the generated complex vector question.
     */
    private static String generateComplexQuestion() {
        int equationLenght = 1 + (int)(Math.random() * 4 + 1);
        int dimension = (int)(Math.random() * 2 + 1);
        String question = "";

        for (int i = 0; i < equationLenght; i++) {
            if (dimension == 1) {
                int operator = (int)(Math.random() * 2 + 1);
                question += " " + randomVecScalar2D();
                if (operator == 1) {
                    question += " +";
                }
                else {
                    question += " -";
                }
            }
            else {
                int operator = (int)(Math.random() * 3 + 1);
                question += " " + randomVecScalar3D();
                if (operator == 1) {
                    question += " +";
                }
                else if (operator == 2) {
                    question += " -";
                }
                else {
                    question += " x";
                }
            }
        }
        return question.substring(0, question.length() - 1).strip();
    }

    /**
     * Returns the correct answer for a given vector equation.
     *
     * @param equation the vector equation to compute the answer for
     * @param type the type of the question
     * @return the correct answer to the vector equation as a string
     * @throws IllegalAccessException if the equation is not a valid vector equation
     */
    private static String getCorrectAnswer(String equation, questionType type) throws IllegalAccessException {
        if (type == questionType.angle) {
            vector vec1 = vector.valueOf(equation.split("and")[0].replace("Find angle between", "").strip());
            vector vec2 = vector.valueOf(equation.split("and")[1].strip());
            return String.valueOf(vector.angle(vec1, vec2));
        }
        return VectorCalculator.calculate(equation);
    }

    /**
     * Check if the given answer is correct for the given equation and question type.
     *
     * @param equation the vector equation for the question
     * @param answer the user's answer to the question
     * @param type the type of vector question
     * @return true if the student's answer is correct, false otherwise
     * @throws IllegalAccessException if the answer cannot be parsed or calculated
     */
    private static boolean isAnswerCorrect(String equation, String answer, questionType type) throws IllegalAccessException {
        if (type == questionType.angle) {
            return answer.strip().equals(getCorrectAnswer(equation, type));
        }
        else {
            String holderAnswer = answer;
            if (!answer.contains("[")) {
                holderAnswer = "[" + answer + "]";
            }

            String rightAnswer = getCorrectAnswer(equation, type);
            if (!rightAnswer.contains("[")) {
                rightAnswer = "[" + rightAnswer + "]";
            }
            return vector.valueOf(holderAnswer).equals(vector.valueOf(rightAnswer));
        }
    }

    /**
     * Runs the program by selecting a question of the given type and prompting the user to solve it.
     * Continues running until the user types "exit".
     * If the user types "answer", the correct answer to the current question is displayed.
     * If the user's answer is correct, the next question is selected and the program continues.
     * If the user's answer is incorrect, the program prompts the user to try again.
     * 
     * @param type of question to generate (cross product, dot product, add, subtract, angle, complex)
     */
    private static void runQuestion(questionType type) {
        printInstructions();

        String userAns = "";

        String question = selectQuestion(type);

        int questionNumber = 1;
            
        while (!userAns.equals("exit")) {
            System.out.println("\n\n" + questionNumber + ". Solve for: " + question);
            System.out.println("Enter Answer: ");
            userAns = UserInput.getValue();

            if (!userAns.equals("exit")) {

                    if (userAns.equals("answer")) {
                        try {
                            String correctAns = getCorrectAnswer(question, type);
                            System.out.println(String.format("\nCorrect answer to question %d was %s", questionNumber, correctAns));
                            UserInput.pressEnter();
                            question = selectQuestion(type);
                            questionNumber++;
                            continue;
                        } catch (Exception e) {
                            System.out.println("Something went wronge in creating the right answer:" + e.getMessage() + ". Try Again.");
                            UserInput.pressEnter();
                            continue;
                        }
                    }
 
                    try {
                        if (isAnswerCorrect(question, userAns, type)) {
                            System.out.println("\nCorrect!");
                            UserInput.pressEnter();
                            questionNumber++;
                            question = selectQuestion(type);
                        }
                        else {
                            System.out.println("\nIncorrect solution. Try Again.");
                            UserInput.pressEnter();
                        }
                    } catch (Exception e) {
                        System.out.println("\nError: " + e.getMessage() + ". Try Again.");
                        UserInput.pressEnter();
                    }
            }
        }
    }

    /**
     * Runs the quiz program. Prompts the user to select a question type to generate a question
     * and receive user input for an answer. 
     */
    public static void runQuiz() {
        String userAns = "";

        printIntro();

        while (!userAns.equals("7")) {
            System.out.println("\nEnter: ");
            userAns = UserInput.getValue();

            if (userAns.equals("1")) {
                runQuestion(questionType.crossProduct);
            }
            else if (userAns.equals("2")) {
                runQuestion(questionType.dotProduct);
            }
            else if (userAns.equals("3")) {
                runQuestion(questionType.subtract);
            }
            else if (userAns.equals("4")) {
                runQuestion(questionType.add);
            }
            else if (userAns.equals("5")) {
                runQuestion(questionType.angle);
            }
            else if (userAns.equals("6")) {
                runQuestion(questionType.complex);
            }
            else if (!userAns.equals("7")) {
                System.out.println("\nWronge Input.");
                UserInput.pressEnter();
            }

            printIntro();
        }
    }

    public static void test() throws IllegalAccessException {
        System.out.println(getCorrectAnswer("Find angle between [9, 1, 8 5/8] and [5 1/4, 6, 5 1/2]", questionType.angle)); //34
        System.out.println(getCorrectAnswer("5/6[3, 7, 8] x 5/6[3, 7, 8]", questionType.crossProduct)); //[0, 0, 0]
        System.out.println(getCorrectAnswer("5/6[3, 7, 8] + 5/6[3, 7, 8]", questionType.add)); //[5, 11 2/3, 13 1/3]
        System.out.println(getCorrectAnswer("5/6[3, 7, 8] - 5/6[3, 7, 8]", questionType.subtract)); //[0, 0, 0]
        System.out.println(getCorrectAnswer("5/6[3, 7, 8] * 5/6[3, 7, 8]", questionType.dotProduct)); //84 13/18
        System.out.println(getCorrectAnswer("5/6[3, 7, 8] + 5/6[3, 7, 8] + 5/6[3, 7, 8] x 5/6[3, 7, 8]", questionType.complex));

        System.out.println(isAnswerCorrect("5/6[3, 7, 8] x 5/6[3, 7, 8]", "[0, 0, 0]", questionType.crossProduct));
        System.out.println(isAnswerCorrect("5/6[3, 7, 8] x 5/6[3, 7, 8]", "[6, 0, 0]", questionType.crossProduct));
        System.out.println(isAnswerCorrect("5/6[3, 7, 8] + 5/6[3, 7, 8]", "[0, 0, 0]", questionType.add));
        System.out.println(isAnswerCorrect("5/6[3, 7, 8] * 5/6[3, 7, 8]", "[84 13/18]", questionType.crossProduct));
        System.out.println(isAnswerCorrect("5/6[3, 7, 8] + 5/6[3, 7, 8] + 5/6[3, 7, 8] x 5/6[3, 7, 8]", "[5, 11 2/3, 13 1/3]", questionType.crossProduct));
    }

    public static void main(String[] args) throws IllegalAccessException {
        test();
    }
}
