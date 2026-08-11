import java.awt.*;

public class Food {

    private Point position;

    public Food(int x, int y) {
        position = new Point(x, y);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // תפוח
        g2.setColor(new Color(220, 50, 50));
        g2.fillOval(position.x + 2, position.y + 4, 21, 19);

        // עלה
        g2.setColor(new Color(70, 180, 80));
        g2.fillOval(position.x + 14, position.y, 9, 6);

        // גבעול
        g2.setColor(new Color(100, 60, 30));
        g2.fillRect(position.x + 12, position.y, 3, 7);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        position.setLocation(x, y);
    }
}