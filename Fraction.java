class Fraction {
    private int numerator;
    private int denominator;


    /**
     * @param numerator Represents numerator - an integer.
     */
    public Fraction(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    /**
     * Describes a Fraction
     * @param numerator Represents numerator - an integer.
     * @param denominator Represents denominator - an integer.
     * @throws IllegalArgumentException If denominator is 0
     */
    public Fraction(int numerator, int denominator) throws IllegalArgumentException {
        if (denominator == 0) throw new IllegalArgumentException("Can't divide by 0");
        this.numerator = numerator/reducedDivider(numerator, denominator);
        this.denominator = denominator/reducedDivider(numerator, denominator);
    }

    /**
     * Constructor for a mixed fraction.
     * @param whole Represents whole value - an integer.
     * @param numerator Represents numerator - an integer.
     * @param denominator Represents denominator - an integer.
     * @throws IllegalArgumentException If denominator is 0
     */
    public Fraction(int whole, int numerator, int denominator) {
        if (denominator == 0) throw new IllegalArgumentException("Can't divide by 0");
        int improperNumerator = toImproperNumerator(whole, numerator, denominator);
        this.numerator = improperNumerator/reducedDivider(improperNumerator, denominator);
        this.denominator = denominator/reducedDivider(improperNumerator, denominator);
    }


    /**
     * Takes in three integers representing a mixed number and converts it to an improper fraction by returning the numerator.
     * 
     * @param whole the whole number in the mixed number
     * @param numerator the numerator in the mixed number
     * @param denominator the denominator in the mixed number
     * @return the numerator of the improper fraction
     */
    private static int toImproperNumerator(int whole, int numerator, int denominator) {
        int convertedNum = (Math.abs(whole) * Math.abs(denominator)) + Math.abs(numerator);

        if (whole*numerator < 0) {
            return convertedNum * -1;
        }
        return convertedNum;
    }

    /**
     * Checks if the numerator or denominator is negative, and return GCD accordingly
     * 
     * @param numerator the numerator of the fraction
     * @param denominator the denominator of the fraction
     * @return Greatest common divisor
     */
    private static int reducedDivider(int numerator, int denominator) {
        int numGCD = gcd(numerator, denominator);

        if (numerator == 0) return 1;

        if ((numerator < 0 && denominator < 0) || (denominator < 0)) {
            return numGCD * -1;
        }
        return numGCD;
    }

    /**
     * calculates the greatest common divisor (GCD) of two integers.
     * 
     * @param valNum an integer representing the numerator value
     * @param valDen an integer representing the denominator value
     * @return the GCD of valNum and valDen, or 0 if either value is 0 or if no common divisor exists
     */
    private static int gcd(int valNum, int valDen) {
        int minNum = Math.min(Math.abs(valNum), Math.abs(valDen));

        for (int testGCD = minNum; testGCD > 0; testGCD--) {
            if (valNum % testGCD == 0 && valDen % testGCD == 0) {
                return testGCD;
            }
        }
        return 0;
    }

    /**
     * Returns the numerator value of a fraction.
     * 
     * @return the numerator value of the fraction
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Returns the denominator value of a fraction.
     * 
     * @return the denominator value of the fraction
     */
    public int getDenominator() {
        return denominator;
    }


    /**
     * Returns the whole value of a fraction.
     * 
     * @return the whole value of the fraction
     */
    public int getWhole() {
        if (numerator < 0 && denominator < 0 || numerator > 0 && denominator > 0){
            return numerator / denominator;
        }
        else {
            return -1 * Math.abs(numerator) / Math.abs(denominator);
        }
    }

    /**
     * Sets the numerator value of a fraction to a new value.
     * 
     * @param newNum the new numerator value to be set
    */
    public void setNumerator(int newNum) {
        numerator = newNum;
    }

    /**
     * Sets the Denominator value of a fraction to a new value.
     * 
     * @param newDen the new Denominator value to be set
    */
    public void setDenominator(int newDen) {
        denominator = newDen;
    }

    /**
     * Determines if a fraction is equal to another fraction.
     * 
     * @param fraction the fraction to be compared to this fraction
     * @return boolean - true if the fractions have the same numerator and denominator values, false otherwise
     */
    public boolean equals(Fraction fraction) {
        return (numerator == fraction.numerator && denominator == fraction.denominator);
    }

    /**
     * Creates a copy of a fraction object.
     * 
     * @param other the fraction object to be cloned
     * @return a new fraction object with the same numerator and denominator values as the original
    */
    public Fraction clone(Fraction other) {
        return other;
    }

    /**
     * Returns a string representation of a fraction.
     * 
     * @return a string representation of the fraction
    */
    public String toString() {
        if (getWhole() == 0 && denominator != 1 && numerator != 0){
            return String.format("%d/%d", numerator, denominator);
        }

        else if (getWhole() != 0 && numerator % denominator != 0){
            return String.format("%d %d/%d", getWhole(), Math.abs(numerator % denominator), Math.abs(denominator));
        }
        else{
            return Integer.toString(getWhole());
        }
    }

    /**
     * Converts a fraction to its equivalent decimal value.
     * 
     * @return the decimal value of the fraction as a double
    */
    public double toDouble() { 
        return Double.valueOf(numerator)/Double.valueOf(denominator);
    }

    /**
     * Adds two fractions and returns the result as a new fraction object.
     * 
     * @param other the fraction to be added to the current fraction
     * @return a new fraction object representing the sum of the two fractions
    */
    public Fraction add(Fraction other) {
        int newNum = numerator * other.denominator + other.numerator * denominator;
        int newDen = other.denominator * denominator;

        return new Fraction(newNum, newDen);
    }

    /**
     * Subtracts two fractions and returns the result as a new fraction object.
     * 
     * @param other the fraction to be subtracted to the current fraction
     * @return a new fraction object representing the difference of the two fractions
    */
    public Fraction subtract(Fraction other) {
        return this.add(new Fraction(other.numerator * -1, other.denominator));
    }

    /**
     * multiplies two fractions and returns the result as a new fraction object.
     * 
     * @param other the fraction to be multiplied to the current fraction
     * @return a new fraction object representing the product of the two fractions
    */
    public Fraction multiply(Fraction other) {
        return new Fraction(numerator * other.numerator, denominator * other.denominator);
    }

    /**
     * Divides two fractions and returns the result as a new fraction object.
     * 
     * @param other the fraction to be divided to the current fraction
     * @return a new fraction object representing the quotient of the two fractions
    */
    public Fraction divide(Fraction other) {
        return new Fraction(numerator * other.denominator, denominator * other.numerator);
    }

    /**
     * Returns a new Fraction object that represents this fraction raised to the
     * specified power.
     *
     * @param power the power to raise this fraction to.
     * @return a new Fraction object that represents this fraction raised to the
     * specified power.
     */
    public Fraction pow(int power) {
        return new Fraction((int)Math.pow(numerator, power), (int)Math.pow(denominator, power));
    }

    /**
     * Returns a string representation of three different forms of a fraction:
     * whole: z, improper: y/z, and mixed x y/z. 
     *
     * @return a string representation of the three forms of a fraction
     */
    public static String fractionForm() {
        return String.format("(%s|%s|%s)", wholeFractionForm(), impropFractionForm(), mixedFractionForm());
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
     * Returns a regular expression pattern string for matching an integer.
     * 
     * @return a regular expression pattern for matching an integer.
     */
    private static String wholeFractionForm() {
        return "-?\\d+";
    }

    /**
     * Returns a regular expression pattern string for matching an improper fraction.
     * 
     * @return a regular expression pattern for matching an improper fraction.
     */
    private static String impropFractionForm() {
        return wholeFractionForm() + space() + "\\/" + space() + wholeFractionForm();
    }

    /**
     * Returns a regular expression pattern string for matching a mixed fraction.
     * 
     * @return a regular expression pattern for matching a mixed fraction.
     */
    private static String mixedFractionForm() {
        return wholeFractionForm() + " " + space() + impropFractionForm();
    }

    /**
     * Returns a random integer between 1 and the specified maximum value (inclusive).
     *
     * @param max the maximum value that the generated number can take.
     * @return a random integer between 1 and the specified maximum value (inclusive).
     */
    private static int generateNum(int max) {
        return (int)(Math.random() * max + 1);
    }


    /**
     * Returns a random Fraction object, which can represent a whole number, a proper
     * fraction, or a mixed fraction
     *
     * @return a random Fraction object
     */
    public static Fraction random() {
        int fracType = generateNum(2);

        if (fracType == 0){
            return new Fraction(generateNum(10));
        }
        else if (fracType == 1){
            return new Fraction(generateNum(10), generateNum(10));
        }
        else{
            return new Fraction(generateNum(10), generateNum(10), generateNum(10));
        }
    }


    /**
     * Returns a fraction object from the input string representation of a fraction.
     * 
     * @param VectorStr the string representation of a fraction in the format of mixed, improper, or whole"
     * @return a new fraction object created from the input string representation
     * @throws IllegalAccessException if the input string does not match the expected format
     */
    public static Fraction valueOf(String fraction) throws IllegalArgumentException {
        if (fraction.strip().matches(wholeFractionForm())) {
            return new Fraction(Integer.valueOf(fraction.strip()), 1);
        }
        else if (fraction.strip().matches(fractionForm())) {

            // spilt the fraction using the "/" in the midde to get the different components 
            String top = fraction.split("/")[0].strip();
            String den = fraction.split("/")[1].strip();
            int denInt = Integer.valueOf(den);

            // if the fraction is mixed we will have to do a bit more work. 
            // We will get the whole component of the fraction using the space bettween the numbers
            // for exmaple: 5 2/4. 
            if (fraction.strip().matches(mixedFractionForm())) {
                String topSplit[] = top.replace("/", "").strip().split(" ");
                String whole = topSplit[0];
                String num = topSplit[topSplit.length - 1];
                int wholeInt = Integer.valueOf(whole);

                int numInt = Integer.valueOf(num);
    
                return new Fraction(wholeInt, numInt, denInt);
            }

            int numInt = Integer.valueOf(top);

            return new Fraction(numInt, denInt);
        }
        else {
            throw new IllegalArgumentException("Invaild Fraction Format");
        }
    }


    /**
     * test expressions
     */
    public static void test() {
        System.out.println(Fraction.valueOf("1/2")); // 1/2
        System.out.println(Fraction.valueOf("10/-12")); // -5/6
        System.out.println(Fraction.valueOf("-120/2")); // -60
        System.out.println(Fraction.valueOf("-51/4")); // -12 3/4
        System.out.println(Fraction.valueOf("-1204/120")); //-10 1/30
        System.out.println(Fraction.valueOf("-2 4/18")); // -2 2/9
        System.out.println(Fraction.valueOf("-2")); // -2
        System.out.println(Fraction.valueOf("-2 110/18")); // -8 1/9
        System.out.println(Fraction.valueOf("0/5")); // 0
        System.out.println(Fraction.valueOf("-0/5")); // 0
        System.out.println(Fraction.valueOf("-4/2")); // -2

        System.out.println(new Fraction(-11, 5).add(new Fraction(-2, 4))); // -2 7/10
        System.out.println(new Fraction(-11, 5).subtract(new Fraction( -2, 4))); // -1 7/10
        System.out.println(new Fraction(-11, 5).multiply(new Fraction(-2, 4))); // 1 1/10
        System.out.println(new Fraction(-11, 5).divide(new Fraction(-2, 4))); // 4 2/5

        System.out.println(new Fraction(-1, 11, 5).add(new Fraction(-2, 3, 4))); // -5 19/20
        System.out.println(new Fraction(-1, 11, 5).subtract(new Fraction(-2, 3, 4))); // -9/20
        System.out.println(new Fraction(-1, 11, 5).multiply(new Fraction(-2, 3, 4))); // 8 4/5
        System.out.println(new Fraction(-1, 11, 5).divide(new Fraction(-2, 3, 4))); // 1 9/55

        System.out.println(new Fraction(-1).add(new Fraction(-2))); // -3
        System.out.println(new Fraction(-1).subtract(new Fraction(-2))); // 1
        System.out.println(new Fraction(-1).multiply(new Fraction(-2))); // 2
        System.out.println(new Fraction(-1).divide(new Fraction(-2))); // 1/2

        System.out.println(new Fraction(-1).add(new Fraction(0))); // -1
        System.out.println(new Fraction(-1).subtract(new Fraction(0))); // -1
        System.out.println(new Fraction(-1).multiply(new Fraction(0))); // 0
        System.out.println(new Fraction(-1).divide(new Fraction(1))); // -1
    }

    public static void main(String[] args) {
        System.out.println(Fraction.valueOf("5 1/-2"));
    }
}