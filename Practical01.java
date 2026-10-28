import java.util.Scanner;

public class Practical01 {
    public static double calculate(double a, double b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("Cannot divide by zero");
                return a / b;
            case "%":
                if (b == 0) throw new ArithmeticException("Cannot divide by zero");
                return a % b;
            default: throw new IllegalArgumentException("Invalid operator");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("First number: ");
            double a = sc.nextDouble();
            System.out.print("Operator (+ - * / %): ");
            String op = sc.next();
            System.out.print("Second number: ");
            double b = sc.nextDouble();
            System.out.println("Result: " + calculate(a, b, op));
        } catch (java.util.InputMismatchException e) {
            System.out.println("Enter valid numbers");
        } catch (ArithmeticException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
