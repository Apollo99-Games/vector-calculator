import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
* Calculates vectors represented by a string
*/
class VectorCalculator {

    /**
     * Calculates the scalar value of a vector and a scalar given as a string.
     *
     * @param VectorStr the string containing the vector and scalar.
     * @return a vector object by multiplying the vector by the scalar.
     * @throws IllegalAccessException if the input string is not in the correct format.
     */
    private static vector calculateScalar(String VectorStr) throws IllegalAccessException {
        vector vec = vector.valueOf(removeScalar(VectorStr));
        Fraction scalar = Fraction.valueOf(removeVector(VectorStr));
        return vec.scalarMultiply(scalar);
    }

    /**
     * Calculates a vector operation between two input strings representing vectors,
     * and returns the resulting vector as a string.
     * 
     * @param first A string representing the first vector to be used in the operation.
     * @param operation A string representing the operation to be performed on the vectors.
     * @param second A string representing the second vector to be used in the operation.
     * @return A string representing the resulting vector after the operation has been performed.
     * @throws IllegalAccessException If the input strings are not formatted correctly, or if the operation is invalid.
     */
    private static String calculateVector(String first, String operation, String second) throws IllegalAccessException {

        vector multiplyedFirst = calculateScalar(first);
        vector multiplyedSecond = calculateScalar(second);

        if (operation.equals("+")) {
            return multiplyedFirst.add(multiplyedSecond).toString();
        }
        else if (operation.equals("-")) {
            return multiplyedFirst.subtract(multiplyedSecond).toString();
        }
        else if (operation.equals("x")) {
            return multiplyedFirst.crossProduct(multiplyedSecond).toString();
        }
        else if (operation.equals("*")) {
            return multiplyedFirst.dotProduct(multiplyedSecond).toString();
        }
        else {
            throw new IllegalAccessException("Operator is not correct");
        }
    }

    /**
     * Removes the vector part from a vector with a scalar string and returns the scalar part.
     * If no scalar is specified in the string, returns "1".
     * 
     * @param Vector a string representing the scalar-vector string
     * @return the scalar component of the string
     */
    private static String removeVector(String Vector) {
        String scalar = Vector.split("\\[")[0];
        if (scalar.length() == 0) {
            return "1";
        }
        return scalar;
    }

    /**
     * Removes the scalar part from a vector with a scalar string and returns the vector part.
     *
     * @param Vector a string representing the scalar-vector string
     * @return the vector component of the input string
     */
    private static String removeScalar(String Vector) {
        return "[" + Vector.split("\\[")[1];
    }

    /**
     * Separates the equation into 3 parts. The 2nd being what needs to be calculated first, the 1st part 
     * is everything that comes before, and the 3rd is everything that comes after. 
     * Note the "important" part is the one which has the most brackets surrounding it.
     * 
     * @param equation a string representing the equation
     * @return a String array containing the equation before the bracket, in, and after
     */
    private static String[] separateImportantBrackets(String equation) {
        String seperated[] = {"", "", ""};
        if (equation.contains("(")) {
            String beforeBracket = equation.substring(0, equation.lastIndexOf("("));
            String inBracket = equation.substring(equation.lastIndexOf("(") + 1, equation.length());
            String afterBracket = inBracket.substring(inBracket.indexOf(")") + 1, inBracket.length());
            inBracket = inBracket.substring(0, inBracket.indexOf(")"));
            
            seperated[0] = beforeBracket.strip();
            seperated[1] = inBracket.strip();
            seperated[2] = afterBracket.strip();

            return seperated;
        }
        else {
            seperated[1] = equation;
            return seperated;
        }
    }

    /**
     * Splits a substring containing a vector operation into its component parts.
     * The substring should have the form "vector operation vector".
     * Returns an array of strings with three elements: the first vector, the operation,
     * the second vector.
     * 
     * @param subEqu the substring to split
     * @return an array of strings with three elements representing the vector operation
     */
    private static String[] spiltOperationPair(String subEqu) {
        String first = subEqu.split("]")[0].strip() + "]";
        String second = subEqu.split("]")[1].strip() + "]";
        String operation = second.substring(0, 1);
        second = second.substring(1, second.length()).strip();

        String splited[] = {first, operation, second};

        return splited;
    }

    /**
     * Returns a regular expression pattern string for matching a scalar-vector formatted string.
     * 
     * @return a regular expression pattern for matching a scalar-vector as a string.
     */
    private static String VectorScalarFormat() {
        return Fraction.fractionForm() + "?" + space() + vector.vectorFormat();
    }

    /**
     * Returns a regular expression pattern string for matching an optional empty space in a string.
     * 
     * @return a regular expression pattern for matching an optional empty space in a string.
     */
    private static String space() {
        return "(\\s+)?";
    }

    /**
     * Returns a regular expression pattern string for matching an equation where an operation
     * occurs between two scalar-vectors. An "@" is used as a place holder for the operation.
     * 
     * @return a regular expression pattern string for matching an equation where an operation
     * occurs between two scalar-vectors. An "@" is used as a place holder for the operation.
     */
    private static String VectorEquFormat() {
        return VectorScalarFormat() + space() + "@" + space() + VectorScalarFormat();
    }

    /**
     * Checks if the "-" symbol at the given index in the equation string
     * is a negative operator or a subtraction operator. 
     * 
     * @param negLoc the index of the "-" symbol in the equation string
     * @param equation the equation string
     * @return boolean - true if the "-" symbol is a negative operator; false if it is a subtraction operator
     */
    private static boolean isNegativeOperator(int negLoc, String equation) {
        String beforeNeg = equation.substring(0, negLoc);
        int closestNeg = beforeNeg.lastIndexOf("-");
        if (negLoc == 0) return false;
        if (closestNeg == -1) return true;

        String brackets[] = {"]", "("}; 
        for (String type : brackets) {
            int distance = beforeNeg.lastIndexOf(type);
            if (distance != -1 && distance < closestNeg) {
                return false;
            }
        }
        return true;
    }

    /**
     * Finds the starting and ending positions of the first specified string in another string using regular expressions.
     *
     * @param findStr the string to search for
     * @param fromStr the string to search in
     * @return an integer array representing the starting and ending positions of 
     * the first match of findStr in fromStr, or [-1, -1] if no match is found
     * @throws IllegalAccessException if the vector format is incorrect
     */
    private static int[] find(String findStr, String fromStr) throws IllegalAccessException {
        Pattern pattern = Pattern.compile(findStr);
        Matcher matcher = pattern.matcher(fromStr);
        int point[] = {-1, -1};
        if (matcher.find()) {
            point[0] = matcher.start();
            point[1] = matcher.end();
        }
        else {
            throw new IllegalAccessException("Vector format is incorrect");
        }
        return point;
    }

    /**
     * Finds a pair of vectors in the equations. Returns a string array containing what comes before the pair,
     * pair itself, and what comes after. 
     *
     * If the character immediately before the pair is a '-', then it is considered part of the pair, unless
     * the '-' is not part of an operator.
     *
     * If the pair is not found in the equation, an IllegalAccessException is thrown.
     *
     * @param findPair the string representing the pair to find
     * @param fromEqu the equation to search in
     * @return a string array of the form {before, pair, after}
     * @throws IllegalAccessException if the pair is not found in the equation
     */
    private static String[] extractPair(String findPair, String fromEqu) throws IllegalAccessException {
        int index[] = find(findPair, fromEqu);

        if (fromEqu.charAt(index[0]) == '-' && isNegativeOperator(index[0], fromEqu)) {
            index[0] += 1;
        }

        String first = fromEqu.substring(0, index[0]);
        String pair = fromEqu.substring(index[0], index[1]);
        String second = fromEqu.substring(index[1], fromEqu.length());
        String splitEqu[] = {first, pair, second};
        return splitEqu;
    }

    /**
     * Finds the next pair of vectors to be calculated in the subequation.
     * Pairs with specifc operations are considered first in this order:
     * Cross product, dot product, then adding or subtracting (which ever comes first)
     * 
     * @param subEqu a String representing the sub-equation to search for the next pair of values.
     * @return a String array containing everything before the pair of vectors, the pair itself,
     * and everything after in the format: {before, pair, after}
     * 
     * @throws IllegalAccessException if the operator is not found
     */
    private static String[] findNextPair(String subEqu) throws IllegalAccessException {
        if (subEqu.contains("x")) {
            return extractPair(VectorEquFormat().replace("@", "x"), subEqu);
        }
        else if (subEqu.contains("*")) {
            return extractPair(VectorEquFormat().replace("@", "\\*"), subEqu);
        }
        else if (subEqu.contains("-") || subEqu.contains("+")) {
            return extractPair(VectorEquFormat().replace("@", "(\\+|\\-)"), subEqu);
        }
        throw new IllegalAccessException("Operator not found");
    }

    /**
     * Checks if the equation is calculated by checking if there is only one vector left
     * 
     * @param subEqu a string representing the subequation
     * @return boolean - true if equation is calculated, else false
     */
    private static boolean continueCalculation(String subEqu) {
        return subEqu.split("]").length > 1;
    }

    /**
     * Runs calculations on a sub-equation by repeatedly finding and calculating the next vector 
     * operation until no more operations can be found.
     * 
     * @param subEqu the sub-equation represented as a string to run calculations on.
     * @return the resulting sub-equation after all vector operations have been calculated.
     * @throws IllegalAccessException if the sub-equation format is incorrect or an operator cannot be found.
     */
    private static String runCalculations(String subEqu) throws IllegalAccessException {
        String calculated = subEqu;
        while (continueCalculation(calculated)) {
            String nextPair[] = findNextPair(calculated);
            String spiltEqua[] = spiltOperationPair(nextPair[1]);
            calculated = nextPair[0] + calculateVector(spiltEqua[0], spiltEqua[1], spiltEqua[2]) + nextPair[2];
        }
        return calculated;
    }

    /**
     * This method takes an equation and handles scalar calculations if necessary. If the vector has a
     * scalar value, it calculates the answer. If the result is a vector with
     * dimension 1, it removes the brackets and returns the result as a scalar.
     *
     * @param equation the equation to handle
     * @return the result of the scalar calculation or the original equation as a scalar
     * @throws IllegalAccessException if the equation is not in the proper scalar format
     */
    private static String handleScalar(String equation) throws IllegalAccessException {
        String equ = equation;

        if (removeVector(equ) != "1") {
            if (!equ.matches(VectorScalarFormat())) throw new IllegalAccessException("Improper Format");
            equ = calculateScalar(equ).toString();
        } 

        if (vector.valueOf(equ).getDimension() == 1) {
            equ = equ.replace("[", "");
            equ = equ.replace("]", "");
        }
        return equ;
    }

    /**
     * Takes a user-provided string and performs vector and scalar calculations based on the operations in the string.
     * 
     * @param userEquation The equation string provided by the user
     * @return A string representation of the calculated result
     * @throws IllegalAccessException If the format of the equation is incorrect or a calculation error occurs
     */
    public static String calculate(String userEquation) throws IllegalAccessException {
        String equation = userEquation.strip();

        while (continueCalculation(equation)) {
            String subEquations[] = separateImportantBrackets(equation);
            String subCalculated = runCalculations(subEquations[1]);
            equation = subEquations[0] + subCalculated + subEquations[2];
        }

        equation = handleScalar(equation);

        return equation;
    }

    /**
     * test expressions
     */
    private static void test() throws IllegalAccessException {
        System.out.println(calculateScalar("-2[5, 7 5/6, 2/8]")); //[-10, -15 2/3, -1/2]
        System.out.println(calculateScalar("1/6[4, 83, 12]")); //[2/3, 13 5/6, 2]

        System.out.println(calculateVector("[4, 7, -7]", "+" , "[4, 7, -7]")); // [8, 14, -14]
        System.out.println(calculateVector("[4, 7, -7]", "-" , "[4, 7, -7]")); // [0, 0, 0] 

        System.out.println(removeVector("-2[5, 7 5/6, 2/8]")); //-2
        System.out.println(removeVector("1/6[4, 83, 12]")); //1/6

        System.out.println(removeScalar("-2[5, 7 5/6, 2/8]")); //[5, 7 5/6, 2/8]
        System.out.println(removeScalar("1/6[4, 83, 12]")); //[4, 83, 12]
        System.out.println(runCalculations("1/6[4, 83, 12] - 12/3[4, -6, 4]")); // [-15 1/3, 37 5/6, -14] 

        System.out.println(separateImportantBrackets("(5/2[5, 3, 9] + 5/7([6, 12, 9] -5 5/7[12/2, 5, 8])) x [2, 3, 4]")[1]); //[6, 12, 9] -5 5/7[12/2, 5, 8]
        System.out.println(Arrays.toString(spiltOperationPair("[4, 7, -7] + [4, 0, 9]"))); // [[4, 7, -7], +, [4, 0, 9]]  
        System.out.println(isNegativeOperator(15 , "1/6[4, 83, 12] - 12/3[4, -6, 4]")); //true
        System.out.println(isNegativeOperator(0 , "-1/6[4, 83, 12] - 12/3[4, -6, 4]")); //false

        System.out.println(Arrays.toString(find("1/3", "1/3[3, -5, 6]"))); // [0, 3]
        System.out.println(Arrays.toString(findNextPair("1/6[4, 83, 12] - 12/3[4, -6, 4] + 1/3[3, -5, 6]"))); //[, 1/6[4, 83, 12] - 12/3[4, -6, 4],  + 1/3[3, -5, 6]]
        System.out.println(continueCalculation("[4, 7, -7] + [4, 0, 9]")); // true
        System.out.println(continueCalculation("[4, 7, -7]")); //false

        System.out.println(handleScalar("[50]")); //50
        System.out.println(handleScalar("10[50, 20]")); //[500, 200]

        System.out.println(calculate("[4, 7, -7] + [4, 0, 9]"));                                         // [8, 7, 2]
        System.out.println(calculate("[-6/5, 7, 2] - [5, 2, 5]"));                                       // [-6 1/5, 5, -3]
        System.out.println(calculate("-2[5, 7 5/6, 2/8]"));                                              // [-10, -15 2/3, -1/2]
        System.out.println(calculate("[3, 7, 8] + [1, 2, 3]"));                                          // [-14 1/3, 36 1/6, -12]
        System.out.println(calculate("1/6[4, 83, 12] - 12/3[4, -6, 4] + 1/3[3, -5, 6]"));                  // [-9, -8 3/4, 66 3/4]
        System.out.println(calculate("[1, -7, 80 3/4] - 2[5, 7/8, 7]"));                                 // [2, -12, -4]
        System.out.println(calculate("[5, -9, 3] - [3, 3, 7]"));                                         // [2 4/7, -3 3/7, 7 5/7]
        System.out.println(calculate("6/7[3, -4, 9]"));                                                    // [13 71/90, 4 7/180, -28 202/315]
        System.out.println(calculate("1 1/5[3/4, 1 3/8, -19] - 1 1/9[1, -2, 5] + 8/6[9, -4/8, -5/7] + 5/6[6, 7, 8] - [3, 5, 6]"));  // [13 71/90, 4 7/180, -28 202/315]

        System.out.println(calculate("[3, 7, 8] * [1, 2, 3]"));                                          // 41
        System.out.println(calculate("2/3[3/3, 7/6, 8] * 5/6[1, 2/8, 3]"));                                          // 14 11/216
        System.out.println(calculate("[4, 12/2, 8/4] x [4, 7/6, 3]"));                                          // [15 2/3, -4, -19 1/3]
        System.out.println(calculate("-2/3[3, 7, 8] x 5/6[5, 2, 67]"));                                          // [-251 2/3, 89 4/9, 16 1/9]
        System.out.println(calculate("5/6[3, 7, 8] x 5/6[3, 7, 8]"));                                          // [0, 0, 0]
        System.out.println(calculate("(5/2[5, 3, 9] + 5/7([6, 12, 9] -5 5/7[12/2, 5, 8])) x [2, 3, 4]")); //[-6 17/98, 23 18/49, -14 43/98]
        System.out.println(calculate("(5 4/3[5, 2, 9] * 5/7[6, 12, 9]) x (5/2[5, 6, 9] * 5/7[6, 12, 9])")); // 199572 69/98
        System.out.println(calculate("[       22     9    /      10     , -2  1  /   25, -98   1  /  5  ] +  5/2[5, 6, 9]")); //[35 2/5, 12 24/25, -75 7/10]
        System.out.println(calculate("(5 4/3[5, 2, 9] x 5/7[6, 12, 9]) - 5/3(5/2[5, 6, 9] x 5/7[6, 12, 9])")); // [-246 3/7, 13 13/14, 145 5/7]
        System.out.println(calculate("(-5 5/7[4, 6]) - -5/4[6, 7]"));


    }

    public static void main(String[] args) throws IllegalAccessException {
        test();
    }
}