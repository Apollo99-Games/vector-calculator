# vector-calculator

This is an implementation of a vector calculator in Java for vectors up to 3D. Note you will need some JDK and preferably an IDE to run the code as it requires a terminal to print the result (I used VS code).
The vector calculator can be run from the "Main.java" file.

You can run the program on [Replit](https://replit.com/@WahhajKhan/Vector-Calculator?v=1).

### The Java Version used to test the code: 
- openjdk version "17.0.11" 2024-04-16
- OpenJDK Runtime Environment Temurin-17.0.11+9 (build 17.0.11+9)
- OpenJDK 64-Bit Server VM Temurin-17.0.11+9 (build 17.0.11+9, mixed mode, sharing)

# Main Calculator interface: 
- To make a Vector, use square brackets like: For 3D Vector: [5, 3, 1], 2D: [3, 4], 1D: [2]
- To do scalar multiplication, put a number before the Vector: 5[6, 4]
- Brackets can also be used for scalar multiplication: 5([6, 4] + [4, 4])
- For operations put the symbol between the Vectors: 5[6, 4] + 5[4, 4] - 6[5, 4]
- For Addition use +, Subtraction use -, Dot product use *, Cross product use x
- The order of operations is scalar multiplication, Cross, Dot, then Addition or Subtraction (whichever comes first)
- Can't do operations on Vectors with different dimensions like: 5[6, 4] + 5[4, 4, 6]
- Can't do operations between Vectors and scalars: 5[6, 4, 6] * 5[4, 4, 6] + 3[5, 6, 6]
- Negative symbols can’t have a space between them like: - 5
- Must be like -5. However, the symbol can have a space if it is an operator like:  5[4, 4, 6] - 3[5, 6, 6]
- To avoid this use brackets like: 5[6, 4, 6] * (5[4, 4, 6] + 3[5, 6, 6])
- Don't use decimals, use fractions: Improper: 7/2[-30/4, 4], Mixed: 3 1/2[-7 1/2, 4]

# Quiz: 
This is a demonstration using the vector VectorCalculator class to create a quiz to test students

- Quiz has 6 different types of questions
- Calculate Cross Product
- Calculate Dot Product
- Calculate Addition
- Calculate subtraction
- Find the angle between two vectors (for this question round to the nearest whole number)
- Complex, which is a mix of add, subtract, scalar multiples, and Cross Product
- To see the correct answer, type in “answer.”
- If the final answer is a vector, put it in vector format: [4, 3, 6]
- If the final answer is a scalar, don't put square brackets: -56
- Don't use decimals; use fractions.

# Using the Vector Calculator for your own projects: 

Here is an example of using the VectorCalculator class to solve an equation:
```java
public class test {
    public static void main(String[] args) {
        try {
            System.out.println(VectorCalculator.calculate("(5 4/3[5, 2, 9] * 5/7[6, 12, 9]) x (5/2[5, 6, 9] * 5/7[6, 12, 9])"));
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
```
Note: the function has to be around a try/catch, as it will throw an exception if the vector format is wrong.

# Using the Vector Class for your own projects: 

You can also use the vector class to do the calculations. Note that there are no brackets in this case, and the order of operations will not be preserved. 
The equation would be calculated from left to right.
```java
public class test {
    public static void main(String[] args) throws IllegalAccessException {

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

        // Note that you can also convert a string to a vector: 
        System.out.println(vector.valueOf("[1, 2 1/2]")); // [1, 2 1/2]
        System.out.println(vector.valueOf("[3 1/2, 1 1/9]")); // [3 1/2, 1 1/9]
        System.out.println(vector.valueOf("[2 -3/7, 3/7]")); // [2 -3/7, 3/7]
    }
}
```
