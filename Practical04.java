import java.util.Scanner;

public class Practical04 {
    static void checkAge(int age) {
        if (age < 18) throw new IllegalArgumentException("Age must be at least 18");
        System.out.println("Eligible");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1 Arithmetic  2 Array index  3 Number format  4 Throw  5 Checked exception");
        try {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter integer numerator and denominator: ");
                    int a = sc.nextInt();
                    int b = sc.nextInt();
                    System.out.println(a / b);
                    break;
                case 2:
                    int[] values = {10, 20, 30};
                    System.out.print("Index (0 to 2): ");
                    System.out.println(values[sc.nextInt()]);
                    break;
                case 3:
                    System.out.print("Enter an integer as text: ");
                    System.out.println(Integer.parseInt(sc.next()));
                    break;
                case 4:
                    System.out.print("Age: ");
                    checkAge(sc.nextInt());
                    break;
                case 5:
                    throw new java.io.IOException("Example checked exception");
                default:
                    System.out.println("Invalid choice");
            }
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic exception: division by zero");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index is out of range");
        } catch (NumberFormatException e) {
            System.out.println("Number format exception: invalid integer text");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.out.println("Input must be an integer");
        } catch (java.io.IOException e) {
            System.out.println("Caught: " + e.getMessage());
        } finally {
            System.out.println("Exception demonstration finished");
        }
    }
}
