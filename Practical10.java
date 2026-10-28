import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;

public class Practical10 extends JPanel {
    static ArrayList<Point> dda(int x1, int y1, int x2, int y2) {
        ArrayList<Point> points = new ArrayList<>();
        int dx = x2-x1, dy = y2-y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        if (steps == 0) { points.add(new Point(x1, y1)); return points; }
        for (int i = 0; i <= steps; i++) {
            int x = (int)Math.round(x1 + (double)i*dx/steps);
            int y = (int)Math.round(y1 + (double)i*dy/steps);
            points.add(new Point(x, y));
        }
        return points;
    }

    static ArrayList<Point> bresenham(int x1, int y1, int x2, int y2) {
        ArrayList<Point> points = new ArrayList<>();
        int dx = Math.abs(x2-x1), dy = Math.abs(y2-y1);
        int sx = x1 < x2 ? 1 : -1, sy = y1 < y2 ? 1 : -1;
        int error = dx-dy;
        while (true) {
            points.add(new Point(x1, y1));
            if (x1 == x2 && y1 == y2) break;
            int twice = 2*error;
            if (twice > -dy) { error -= dy; x1 += sx; }
            if (twice < dx) { error += dx; y1 += sy; }
        }
        return points;
    }

    static void styled(Graphics g, ArrayList<Point> points, String style) {
        for (int i = 0; i < points.size(); i++) {
            if (style.equals("dotted") && i % 6 != 0) continue;
            if (style.equals("dashed") && i % 16 >= 9) continue;
            Point p = points.get(i);
            int width = style.equals("thick") ? 5 : style.equals("dotted") ? 2 : 1;
            g.fillRect(p.x-width/2, p.y-width/2, width, width);
        }
    }

    static void rectangle(Graphics g, int x, int y, int w, int h, String style) {
        styled(g, dda(x,y,x+w,y), style);
        styled(g, dda(x+w,y,x+w,y+h), style);
        styled(g, dda(x+w,y+h,x,y+h), style);
        styled(g, dda(x,y+h,x,y), style);
    }

    static void diamond(Graphics g, int cx, int cy, int r, String style) {
        styled(g, bresenham(cx,cy-r,cx+r,cy), style);
        styled(g, bresenham(cx+r,cy,cx,cy+r), style);
        styled(g, bresenham(cx,cy+r,cx-r,cy), style);
        styled(g, bresenham(cx-r,cy,cx,cy-r), style);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString("DDA: dotted rectangle + thick rectangle", 80, 35);
        rectangle(g, 60, 65, 320, 210, "dotted");
        rectangle(g, 110, 110, 220, 120, "thick");
        g.drawString("Bresenham: dashed + solid diamonds", 440, 35);
        diamond(g, 590, 170, 110, "dashed");
        diamond(g, 590, 170, 65, "solid");
    }

    public static void main(String[] args) throws Exception {
        if (args.length > 0 && args[0].equals("--save")) {
            Practical10 panel = new Practical10();
            panel.setBackground(Color.WHITE);
            panel.setSize(800, 340);
            BufferedImage image = new BufferedImage(800, 340, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            panel.paint(g);
            g.dispose();
            ImageIO.write(image, "png", new File("Practical10_pattern.png"));
            System.out.println("Saved Practical10_pattern.png");
            return;
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("DDA and Bresenham Pattern");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Practical10 panel = new Practical10();
            panel.setBackground(Color.WHITE);
            frame.add(panel);
            frame.setSize(800, 380);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
