import java.awt.*;
import javax.swing.*;
import java.util.Scanner;

public class Practical09 {
    static int code(double x, double y, double xmin, double ymin, double xmax, double ymax) {
        int c = 0;
        if (x < xmin) c |= 1;
        if (x > xmax) c |= 2;
        if (y < ymin) c |= 4;
        if (y > ymax) c |= 8;
        return c;
    }

    static double[] clip(double x1, double y1, double x2, double y2,
                         double xmin, double ymin, double xmax, double ymax) {
        while (true) {
            int c1 = code(x1, y1, xmin, ymin, xmax, ymax);
            int c2 = code(x2, y2, xmin, ymin, xmax, ymax);
            if ((c1 | c2) == 0) return new double[]{x1, y1, x2, y2};
            if ((c1 & c2) != 0) return null;
            int outside = c1 != 0 ? c1 : c2;
            double x, y;
            if ((outside & 8) != 0) {
                y = ymax;
                x = x1 + (x2-x1)*(ymax-y1)/(y2-y1);
            } else if ((outside & 4) != 0) {
                y = ymin;
                x = x1 + (x2-x1)*(ymin-y1)/(y2-y1);
            } else if ((outside & 2) != 0) {
                x = xmax;
                y = y1 + (y2-y1)*(xmax-x1)/(x2-x1);
            } else {
                x = xmin;
                y = y1 + (y2-y1)*(xmin-x1)/(x2-x1);
            }
            if (outside == c1) { x1 = x; y1 = y; }
            else { x2 = x; y2 = y; }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Window xmin ymin xmax ymax:");
        double xmin = sc.nextDouble(), ymin = sc.nextDouble();
        double xmax = sc.nextDouble(), ymax = sc.nextDouble();
        if (xmin >= xmax || ymin >= ymax) { System.out.println("Invalid window"); return; }
        System.out.println("Line x1 y1 x2 y2:");
        double x1 = sc.nextDouble(), y1 = sc.nextDouble();
        double x2 = sc.nextDouble(), y2 = sc.nextDouble();
        double[] result = clip(x1, y1, x2, y2, xmin, ymin, xmax, ymax);
        if (result == null) System.out.println("Line rejected");
        else System.out.printf("Clipped line: (%.2f, %.2f) to (%.2f, %.2f)%n", result[0], result[1], result[2], result[3]);
        if (GraphicsEnvironment.isHeadless()) return;
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cohen-Sutherland | Blue: original, Red: clipped");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new JPanel() {
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D)g;
                    g2.translate(getWidth()/2, getHeight()/2);
                    g2.scale(1, -1);
                    g2.setColor(Color.BLACK);
                    g2.drawRect((int)xmin, (int)ymin, (int)(xmax-xmin), (int)(ymax-ymin));
                    g2.setColor(Color.BLUE);
                    g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
                    if (result != null) {
                        g2.setColor(Color.RED);
                        g2.setStroke(new BasicStroke(3));
                        g2.drawLine((int)result[0], (int)result[1], (int)result[2], (int)result[3]);
                    }
                }
            });
            frame.setSize(800, 650);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
