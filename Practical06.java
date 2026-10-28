import java.awt.*;
import javax.swing.*;
import java.util.Scanner;

public class Practical06 {
    static double[][] transform(double[][] points, int choice, double a, double b) {
        double[][] result = new double[points.length][2];
        double angle = Math.toRadians(a);
        for (int i = 0; i < points.length; i++) {
            double x = points[i][0], y = points[i][1];
            if (choice == 1) {
                result[i][0] = x + a;
                result[i][1] = y + b;
            } else if (choice == 2) {
                result[i][0] = x * Math.cos(angle) - y * Math.sin(angle);
                result[i][1] = x * Math.sin(angle) + y * Math.cos(angle);
            } else if (choice == 3) {
                result[i][0] = x + a * y;
                result[i][1] = y + b * x;
            } else if (choice == 4) {
                result[i][0] = a * x;
                result[i][1] = b * y;
            } else {
                throw new IllegalArgumentException("Invalid transformation");
            }
        }
        return result;
    }
    static void polygon(Graphics g, double[][] points) {
        for (int i = 0; i < points.length; i++) {
            int j = (i + 1) % points.length;
            g.drawLine((int)Math.round(points[i][0]), (int)Math.round(points[i][1]),
                       (int)Math.round(points[j][0]), (int)Math.round(points[j][1]));
        }
    }

    static void show(double[][] original, double[][] changed, String title) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(title + " | Blue: original, Red: transformed");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new JPanel() {
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.translate(getWidth() / 2, getHeight() / 2);
                    g.setColor(Color.LIGHT_GRAY);
                    g.drawLine(-getWidth()/2, 0, getWidth()/2, 0);
                    g.drawLine(0, -getHeight()/2, 0, getHeight()/2);
                    Graphics2D g2 = (Graphics2D)g;
                    g2.scale(1, -1);
                    g2.setColor(Color.BLUE);
                    polygon(g2, original);
                    g2.setColor(Color.RED);
                    polygon(g2, changed);
                }
            });
            frame.setSize(800, 650);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    static void printPoints(double[][] points) {
        for (double[] p : points) System.out.printf("(%.2f, %.2f)%n", p[0], p[1]);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of polygon vertices (at least 3): ");
        int n = sc.nextInt();
        if (n < 3) { System.out.println("At least 3 vertices required"); return; }
        double[][] points = new double[n][2];
        System.out.println("Enter x y for each vertex:");
        for (int i = 0; i < n; i++) {
            points[i][0] = sc.nextDouble();
            points[i][1] = sc.nextDouble();
        }
        System.out.println("1 Translation  2 Rotation  3 Shearing  4 Scaling");
        int choice = sc.nextInt();
        double a, b = 0;
        if (choice == 1) System.out.println("Enter tx and ty:");
        else if (choice == 2) System.out.println("Enter counterclockwise angle in degrees:");
        else if (choice == 3) System.out.println("Enter X shear and Y shear (use 0 for unused axis):");
        else if (choice == 4) System.out.println("Enter X scale and Y scale (use 1 for unchanged axis):");
        else { System.out.println("Invalid choice"); return; }
        a = sc.nextDouble();
        if (choice != 2) b = sc.nextDouble();
        double[][] changed = transform(points, choice, a, b);
        System.out.println("Transformed vertices:");
        printPoints(changed);
        if (!GraphicsEnvironment.isHeadless()) show(points, changed, "2D Polygon Transformations");
    }
}
