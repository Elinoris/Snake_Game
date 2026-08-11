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

        // גוף הנחש
        g.setColor(Color.GREEN);

        for (int i = 1; i < body.size(); i++) {
            Point part = body.get(i);
            g.fillRect(part.x, part.y, 25, 25);
        }

        // ראש הנחש
        g.setColor(Color.RED);

        Point head = body.get(0);
        g.fillRect(head.x, head.y, 25, 25);
    }
    public void move() {
        for (int i = body.size() - 1; i > 0; i--) {
            body.get(i).x = body.get(i - 1).x;
            body.get(i).y = body.get(i - 1).y;
        }

        body.get(0).x += dx;
        body.get(0).y += dy;
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
    public boolean hitWall(int width, int height) {
        Point head = body.get(0);

        return head.x < 0 ||
                head.x >= width ||
                head.y < 0 ||
                head.y >= height;
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
