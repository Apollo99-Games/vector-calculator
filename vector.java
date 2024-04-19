
/** 
* Representes a vector with 1 or more fractions
*/
public class vector {
    private Fraction x;
    private Fraction y;
    private Fraction z;
    private int dimension;

    /**
     * Creates a 1-dimensional vector with the given x-coordinate.
     *
     * @param x the x-coordinate of the vector - a Fraction.
     */
    public vector(Fraction x) {
        this.x = x;
        this.y = new Fraction(0);;
        this.z = new Fraction(0);
        dimension = 1;
    }

    /**
     * Creates a 2-dimensional vector with the given x and y-coordinates.
     *
     * @param x the x-coordinate of the vector - a Fraction.
     * @param y the y-coordinate of the vector - a Fraction.
     */
    public vector(Fraction x, Fraction y) {
        this.x = x;
        this.y = y;
        this.z = new Fraction(0);
        dimension = 2;
    }

    /**
     * Creates a 3-dimensional vector with the given x, y, and z-coordinates.
     *
     * @param x the x-coordinate of the vector - a Fraction.
     * @param y the y-coordinate of the vector - a Fraction.
     * @param z the z-coordinate of the vector - a Fraction.
     */
    public vector(Fraction x, Fraction y, Fraction z) {
        this.x = x;
        this.y = y;
        this.z = z;
        dimension = 3;
    }

    /**
     * Adds this vector to the specified vector and returns the resulting vector.
     *
     * @param other the vector to add to this vector.
     * @return the vector resulting from the addition of this vector and the specified vector.
     * @throws IllegalAccessException if the specified vector is not the same dimension as this vector.
     */
    public vector add(vector other) throws IllegalAccessException {
        if (!isDimensionSame(other)) throw new IllegalAccessException("Vectors are not the Same Dimensions");
        vector newVec = new vector(x.add(other.x), y.add(other.y), z.add(other.z));
        newVec.dimension = dimension;
        return newVec;
    }

    /**
     * Subtracts the specified vector from this vector and returns the resulting vector.
     *
     * @param other the vector to subtract from this vector.
     * @return the vector resulting from the subtraction of the specified vector from this vector.
     * @throws IllegalAccessException if the specified vector is not the same dimension as this vector.
     */
    public vector subtract(vector other) throws IllegalAccessException {
        if (!isDimensionSame(other)) throw new IllegalAccessException("Vectors are not the Same Dimensions");
        vector newVec = new vector(x.subtract(other.x), y.subtract(other.y), z.subtract(other.z));
        newVec.dimension = dimension;
        return newVec;
    }

    /**
     * Multiplies this vector by the specified scalar value and returns the resulting vector.
     *
     * @param multiple the scalar value to multiply this vector by - an integer.
     * @return the vector resulting from the multiplication of this vector by the specified scalar value.
     */
    public vector scalarMultiply(Fraction multiple) {
        vector newVec = new vector(x.multiply(multiple), y.multiply(multiple), z.multiply(multiple));
        newVec.dimension = dimension;
        return newVec;
    }

    /**
     * calculate the dot product of this vector and the specified vector and returns the resulting scalar value as a vector.
     *
     * @param other the vector to calculate the dot product with.
     * @return a vector representing the scalar value resulting from the dot product of this vector and the specified vector.
     * @throws IllegalAccessException if the specified vector is not the same dimension as this vector.
     */
    public vector dotProduct(vector other) throws IllegalAccessException {
        if (!isDimensionSame(other)) throw new IllegalAccessException("Vectors are not the Same Dimensions");
        return new vector(x.multiply(other.x).add(y.multiply(other.y)).add(z.multiply(other.z)));
    }

    /**
     * Calculates the dot product of this vector and another one and returns the result as a new vector.
     *
     * @param other the vector to calculate the dot product with.
     * @return the result of the dot product as a new vector.
     * @throws IllegalAccessException if the two vectors are not the same dimension.
     */
    public vector crossProduct(vector other) throws IllegalAccessException {
        if (isDimensionSame(other)) {
            if (dimension == 1) {
                return dotProduct(other);
            }
            else if (dimension == 3) {
                Fraction xCross = y.multiply(other.z).subtract(z.multiply(other.y));
                Fraction yCross = z.multiply(other.x).subtract(x.multiply(other.z));
                Fraction zCross = x.multiply(other.y).subtract(y.multiply(other.x));
                return new vector(xCross, yCross, zCross);
            }
        }
        throw new IllegalAccessException("One or more Vector not 3 Dimensional");
    }

    /**
     * Checks if this vector is equal to another one.
     *
     * @param other the vector object to compare to.
     * @return boolean - true if the vectors are equal, false otherwise.
     * @throws IllegalAccessException if the two vectors are not the same dimension.
     */
    public boolean equals(vector other) throws IllegalAccessException {
        if (isDimensionSame(other)) {
            return x.equals(other.x) && y.equals(other.y) && z.equals(other.z);
        }
        throw new IllegalAccessException("Vectors are not the Same Dimensions");
    }

    /**
     * Converts vector to a String.
     * 1D: [x], 
     * 2D: [x, y], 
     * 3D: [x, y, z]
     * @return a string that represents the vector object.
     */
    public String toString() {
        if (dimension == 1){
            return String.format("[%s]", x.toString());
        }
        else if (dimension == 2){
            return String.format("[%s, %s]", x.toString(), y.toString());
        }
        return String.format("[%s, %s, %s]", x.toString(), y.toString(), z.toString());
    }

    /**
     * Returns the x-component of this vector.
     *
     * @return the x-component of this vector - a Fraction.
     */
    public Fraction getX() {
        return x;
    }

    /**
     * Returns the y-component of this vector - a Fraction.
     *
     * @return the y-component of this vector - a Fraction.
     * @throws IllegalAccessException if the vector has no y-component.
     */
    public Fraction getY() throws IllegalAccessException {
        if (dimension >= 2) {
            return y;
        }
        throw new IllegalAccessException("Vector has no Y component as it is " + dimension + "D");
    }

    /**
     * Returns the z-component of this vector - a Fraction.
     *
     * @return the z-component of this vector - a Fraction.
     * @throws IllegalAccessException if the vector has no z-component.
     */
    public Fraction getZ() throws IllegalAccessException {
        if (dimension == 3) {
            return z;
        }
        throw new IllegalAccessException("Vector has no Z component as it is " + dimension + "D");
    }

    /** 
     * Returns the dimension of this vector.
     * 
     * @return the dimension of the vector object as an integer.
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Checks if the dimension of this vector is the same as another vector's dimension.
     * 
     * @param second The vector object to compare with.
     * @return boolean - True if the dimension of this vector is the same as the other vector's dimension, false otherwise.
     */
    public boolean isDimensionSame(vector second) {
        return dimension == second.dimension;
    }

    /**
     * Calculates the magnitude of a 3-dimensional vector.
     *
     * @param Vector the vector object to calculate the magnitude of.
     * @return the magnitude of the vector as a double.
     */
    public static double abs(vector Vector) {
        return Math.sqrt(Vector.x.pow(2).add(Vector.y.pow(2)).add(Vector.z.pow(2)).toDouble());
    }

    /**
     * Returns a regular expression pattern string for matching a vector formatted string.
     * 
     * @return a regular expression pattern for matching a vector as a string.
     */
    public static String vectorFormat() {
        String oneTerm = "[^\\],]+";
        return String.format("\\[%s(,%s)?(,%s)?\\]", oneTerm, oneTerm, oneTerm);
    }

    /**
     * Checks if the given vector represents a 3-dimensional vector in the format [x, y, z].
     * 
     * @param VectorStr the string to check
     * @return boolean - true if the string is in the format of a 3-dimensional vector, false otherwise
     */
    private static boolean isVector3DFormat(String VectorStr) {
        String format = "\\[[^,]+,[^,]+,[^,]+\\]";
        return VectorStr.strip().matches(format);
    }

    /**
     * Checks if the given vector represents a 2-dimensional vector in the format [x, y]. 
     * 
     * @param VectorStr the string to check
     * @return boolean - true if the string is in the format of a 2-dimensional vector, false otherwise
     */
    private static boolean isVector2DFormat(String VectorStr) {
        String format = "\\[[^,]+,[^,]+\\]";
        return VectorStr.strip().matches(format);
    }

    /**
     * Checks if the given vector represents a 1-dimensional vector in the format [x].
     * 
     * @param VectorStr the string to check
     * @return boolean - true if the string is in the format of a 1-dimensional vector, false otherwise
     */
    private static boolean isVector1DFormat(String VectorStr) {
        String format = "\\[[^,]+\\]";
        return VectorStr.strip().matches(format);
    }

    /**
     * Calculates the angle in degrees between two vectors.
     * 
     * @param Vector the first vector object
     * @param Vector2 the second vector object
     * @return the angle in degrees between the two vectors - an integer
     * @throws IllegalAccessException if the two vectors do not have the same dimensions
     */
    public static int angle(vector Vector, vector Vector2) throws IllegalAccessException {
        double topAns = Vector.dotProduct(Vector2).getX().toDouble();
        double bottumAns = abs(Vector) * abs(Vector2);
        return (int)Math.round(Math.toDegrees(Math.acos(topAns/bottumAns)));
    }

    /**
     * Checks if the string represents a valid vector format and returns the dimension of the vector.
     *
     * @param vec a string representation of a vector.
     * @return the dimension of the vector as 1, 2 or 3 - as an integer.
     * @throws IllegalAccessException if the vector format is incorrect.
     */
    private static int checkStringDimension(String vec) throws IllegalAccessException {
        if (isVector3DFormat(vec)) {
            return 3;
        }
        else if (isVector2DFormat(vec)) {
            return 2;
        }
        else if (isVector1DFormat(vec)) {
            return 1;
        }
        throw new IllegalAccessException("Vector format is incorrect");
    }

    /**
     * Returns a random 2D vector.
     *
     * @return a random 2D vector object.
     */
    public static vector random2D() {
        return new vector(Fraction.random(), Fraction.random());
    }

    /**
     * Returns a random 3D vector.
     *
     * @return a random 3D vector object.
     */
    public static vector random3D() {
        return new vector(Fraction.random(), Fraction.random(), Fraction.random());
    }

    /**
     * Returns a vector object from the input string representation of a vector.
     * 
     * @param VectorStr the string representation of a vector in the format "[x, y, z]" or "[x, y]" or "[x]"
     * @return a new vector object created from the input string representation
     * @throws IllegalAccessException if the input string does not match the expected format
     */
    public static vector valueOf(String VectorStr) throws IllegalAccessException {
        if (VectorStr.matches(vectorFormat())) {

            // remove brackets
            String VectorM = VectorStr.replace("[", "");
            VectorM = VectorM.replace("]", "");

            Fraction x = null;
            Fraction y = null;
            Fraction z = null;

            int D = checkStringDimension(VectorStr);

            // Spilt the vector strings apart using the commas and create a new vector based on the string's dimensions 
            if (D >= 1) {
                x = Fraction.valueOf(VectorM.split(",")[0]);
                if (D == 1) return new vector(x);
            }
            if (D >= 2) {
                y = Fraction.valueOf(VectorM.split(",")[1].replace(",", ""));
                if (D == 2) return new vector(x, y);
            }

            z = Fraction.valueOf(VectorM.split(",")[2].replace(",", ""));
            return new vector(x, y, z);
        }
        else {
            throw new IllegalAccessException("Invaild Vector Format");
        }
    }

    /**
     * test expressions
     */
    public static void test() throws IllegalAccessException {
        System.out.println(vector.valueOf("[1, 2 1/2]")); // [1, 2 1/2]
        System.out.println(vector.valueOf("[3 1/2, 1 1/9]")); // [3 1/2, 1 1/9]
        System.out.println(vector.valueOf("[2 -3/7, 3/7]")); // [2 -3/7, 3/7]
        System.out.println(vector.valueOf("[1 1/3, 2/5]")); // [1 1/3, 2/5]
        System.out.println(vector.valueOf("[5 1/3, 4 1/2]")); // [5 1/3, 4 1/2]
        System.out.println(vector.valueOf("[1/3, 3]")); // [1/3, 3]
        System.out.println(vector.valueOf("[9, 4 1/2, 7 3/4]")); // [9, 4 1/2, 7 3/4]
        System.out.println(vector.valueOf("[7/8, 7/9, 3 1/3]")); // [7/8, 7/9, 3 1/3]
        System.out.println(vector.valueOf("[1, 3/5, 3]")); // [1, 3/5, 3]
        System.out.println(vector.valueOf("[7 7/9, 4 1/2, 2 1/3]")); // [7 7/9, 4 1/2, 2 1/3]
        System.out.println(vector.valueOf("[3 1/2, 6/7, 1/3]")); // [3 1/2, 6/7, 1/3]
        System.out.println(vector.valueOf("[3/5]")); // [3/5]
        System.out.println(vector.valueOf("[4 1/2]")); // [4 1/2]
        System.out.println(vector.valueOf("[3 1/2]")); // [3 1/2]

        vector a = new vector(new Fraction(4), new Fraction(3), new Fraction(0)); 
        vector b = new vector(new Fraction(8), new Fraction(-4), new Fraction(5));
        Fraction m = new Fraction(4, 3); 
        System.out.println(a.add(b)); //[12, -1, 5]
        System.out.println(a.subtract(b)); //[-4, 7, -5]
        System.out.println(a.dotProduct(b)); //[20]
        System.out.println(a.crossProduct(b)); //[15, -20, -40]
        System.out.println(a.scalarMultiply(m)); // [5 1/3, 4, 0]
        System.out.println(vector.abs(a)); // 5.0
        System.out.println(vector.abs(b)); // 10.246950765959598
        System.out.println(vector.angle(a, b)); //67

        vector c = new vector(new Fraction(9,4,4), new Fraction(4), new Fraction(4,-3)); 
        vector d = new vector(new Fraction(3,1,7), new Fraction(0), new Fraction(7,5));
        Fraction f1 = new Fraction(3,4);
        System.out.println(c.add(d)); //[13 1/7, 4, 1/15]
        System.out.println(c.subtract(d)); //[6 6/7, 4, -2 11/15]
        System.out.println(c.dotProduct(d)); //[29 59/105]
        System.out.println(c.crossProduct(d)); //[5 3/5, -18 4/21, -12 4/7]
        System.out.println(c.scalarMultiply(f1)); // [7 1/2, 3, -1]
        System.out.println(vector.angle(c, d)); //38

        vector e = new vector(new Fraction(0), new Fraction(0,0 ,1), new Fraction(1, 4));
        vector f = new vector(new Fraction(1, 3 ,5), new Fraction (20),new Fraction(2));
        Fraction m2 = new Fraction(3,6,2);
        System.out.println(e.add(f)); //[1 3/5, 20, 2 1/4]
        System.out.println(e.subtract(f)); //[-1 3/5, -20, -1 3/4]
        System.out.println(e.dotProduct(f)); //[1/2]
        System.out.println(e.crossProduct(f)); //[-5, 2/5, 0]
        System.out.println(e.scalarMultiply(m2)); //[0, 0, 1 1/2]
        System.out.println(vector.angle(e, f)); //84

        vector g = new vector(new Fraction(1), new Fraction(6,7 ,8), new Fraction(14, 6));
        vector h = new vector(new Fraction(2, 3 ,6), new Fraction (18),new Fraction(12));
        Fraction m3 = new Fraction(3,6,2);
        System.out.println(g.add(h)); //[3 1/2, 24 7/8, 14 1/3]
        System.out.println(g.subtract(h)); //[-1 1/2, -11 1/8, -9 2/3]
        System.out.println(g.dotProduct(h)); //[154 1/4]
        System.out.println(g.crossProduct(h)); //[40 1/2, -6 1/6, 13/16]
        System.out.println(g.scalarMultiply(m3)); //[6, 41 1/4, 14]
        System.out.println(vector.angle(g, h)); //15

    }

    public static void main(String[] args) throws IllegalAccessException {
        System.out.println("\"" + vector.random3D() +"\"" );
        test();
    }
}