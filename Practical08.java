import java.awt.*;
import javax.swing.*;
import java.util.Scanner;

public class Practical08 {
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
    static double[][] rotate(double[][] points, double angle, double px, double py, boolean xAxis) {
        double[][] result = new double[points.length][2];
        double c = Math.cos(Math.toRadians(angle));
        double s = Math.sin(Math.toRadians(angle));
        for (int i = 0; i < points.length; i++) {
            double x = points[i][0], y = points[i][1];
            if (xAxis) {
                result[i][0] = x;
                result[i][1] = y * c;
            } else {
                result[i][0] = px + (x-px)*c - (y-py)*s;
                result[i][1] = py + (x-px)*s + (y-py)*c;
            }
        }
        return result;
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
        System.out.println("1 About X-axis in 3D (XY projection)  2 About arbitrary point in 2D");
        int choice = sc.nextInt();
        if (choice != 1 && choice != 2) { System.out.println("Invalid choice"); return; }
        System.out.print("Angle in degrees: ");
        double angle = sc.nextDouble();
        double px = 0, py = 0;
        if (choice == 2) {
            System.out.print("Pivot x y: ");
            px = sc.nextDouble();
            py = sc.nextDouble();
        } else {
            System.out.println("For original z=0: x'=x, y'=y*cos(angle), z'=y*sin(angle)");
            System.out.println("The window shows the XY projection. At 180 degrees this reflects about X.");
            for (double[] p : points) System.out.printf("3D: (%.2f, %.2f, %.2f)%n", p[0], p[1]*Math.cos(Math.toRadians(angle)), p[1]*Math.sin(Math.toRadians(angle)));
        }
        double[][] changed = rotate(points, angle, px, py, choice == 1);
        printPoints(changed);
        if (!GraphicsEnvironment.isHeadless()) show(points, changed, "Rotation");
    }
}
