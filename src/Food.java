
import java.awt.*;

public class Food {

    private Point position;

    public Food(int x, int y) {
        position = new Point(x, y);
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(position.x, position.y, 25, 25);
    }

    public Point getPosition() {
        return position;
    }
    public void setPosition(int x, int y) {
        position.setLocation(x, y);
    }
}
