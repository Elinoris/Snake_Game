

import java.awt.*;
import java.util.ArrayList;

public class Snake {

    private int dx = 25;
    private int dy = 0;
    private String direction = "RIGHT";

    private ArrayList<Point> body;

    public Snake() {
        body = new ArrayList<>();

        body.add(new Point(175, 100));
        body.add(new Point(150, 100));
        body.add(new Point(125, 100));
        body.add(new Point(100, 100));
    }


    public void draw(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        // גוף הנחש
        g2.setColor(new Color(71, 127, 255));

        for (int i = 1; i < body.size() - 1; i++) {
            Point part = body.get(i);
            g2.fillRect(part.x, part.y, 25, 25);
        }

        // ראש הנחש
        Point head = body.get(0);

        g2.setColor(new Color(50, 100, 200));
        g2.fillRect(head.x, head.y, 25, 25);

        // עיניים
        g2.setColor(Color.WHITE);

        g2.fillOval(head.x + 5, head.y + 5, 6, 6);
        g2.fillOval(head.x + 14, head.y + 5, 6, 6);

        // אישונים
        g2.setColor(Color.BLACK);

        g2.fillOval(head.x + 7, head.y + 7, 3, 3);
        g2.fillOval(head.x + 16, head.y + 7, 3, 3);
    }



    public boolean move(int width, int height, int topBoundary) {
        Point head = body.get(0);

        int nextX = head.x + dx;
        int nextY = head.y + dy;

        if (nextX < 0 || nextX + 25 > width || nextY < topBoundary || nextY + 25 > height) {
            return false;
        }

        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).x = body.get(i - 1).x;
            body.get(i).y = body.get(i - 1).y;
        }

        head.x = nextX;
        head.y = nextY;

        return true;
    }

    public void changeDirection(int dx, int dy) {

        if (dx == 25 && direction.equals("LEFT")) {
            return;
        }

        if (dx == -25 && direction.equals("RIGHT")) {
            return;
        }

        if (dy == 25 && direction.equals("UP")) {
            return;
        }

        if (dy == -25 && direction.equals("DOWN")) {
            return;
        }

        this.dx = dx;
        this.dy = dy;

        if (dx > 0) {
            direction = "RIGHT";
        } else if (dx < 0) {
            direction = "LEFT";
        } else if (dy > 0) {
            direction = "DOWN";
        } else {
            direction = "UP";
        }
    }

    public boolean isEating(Food food) {
        return body.get(0).equals(food.getPosition());
    }

    public void grow() {
        Point tail = body.get(body.size() - 1);
        body.add(new Point(tail.x, tail.y));
    }

    public boolean hitItself() {
        Point head = body.get(0);

        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }

        return false;
    }

    public boolean containsPosition(Point position) {
        return body.contains(position);
    }
}