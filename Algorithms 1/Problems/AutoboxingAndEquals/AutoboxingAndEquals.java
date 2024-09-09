/*
Question
Java autoboxing and equals().
Consider two double values a and b and their corresponding Double values x and y.
Find values such that (a==b) is true but x.equals(y) is false.
Find values such that (a==b) is false but x.equals(y) is true.
*/

public class AutoboxingAndEquals {
    public static void main(String[] args) {
        // Case: 1
        double a1 = 0.0;
        double b1 = -0.0;
        Double x1 = a1;
        Double y1 = b1;

        System.out.println("Case: 1");
        System.out.println("a==b: " + (a1==b1));        // true
        System.out.println("x.equals(y): " + (x1.equals(y1))); // false
        
        // Case: 2
        double a2 = Double.NaN;
        double b2 = Double.NaN;
        Double x2 = a2;
        Double y2 = b2;

        System.out.println("Case: 2");
        System.out.println("a==b: " + (a2==b2));        // false
        System.out.println("x.equals(y): " + (x2.equals(y2))); // true
    }
}

/*
Explanation:
Case 1 Explanation:
1. 0.0 == -0.0 is true because, numerically, they are considered the same in IEEE 754 floating-point arithmetic.
2. However, Double.valueOf(0.0).equals(Double.valueOf(-0.0)) is false because the Double class distinguishes between 0.0 and -0.0.

Case 2 Explanation:
1. NaN == NaN is false because NaN is not equal to any value, including itself.
2. However, Double.valueOf(Double.NaN).equals(Double.valueOf(Double.NaN)) is true because the .equals() method in Double checks for value equality and correctly identifies that both objects represent NaN.
*/
