
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Snake snake;
    private Food food;
    private Thread gameThread;    private int score = 0;
    private boolean gameOver = false;

    private final int CELL_SIZE = 25;
    private final int BOARD_WIDTH = 600;
    private final int BOARD_HEIGHT = 600;

    public GamePanel() {
        setBackground(Color.BLACK);

        snake = new Snake();
        food = new Food(300, 200);

        setupKeyBindings();

        gameThread = new Thread(() -> {

            while (!gameOver) {

                snake.move();

                if (snake.isEating(food)) {
                    snake.grow();
                    score += 10;
                    moveFood();
                }

                if (snake.hitWall(BOARD_WIDTH, BOARD_HEIGHT)
                        || snake.hitItself()) {

                    gameOver = true;
                }

                repaint();

                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        gameThread.start();
    }

    private void setupKeyBindings() {

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("UP"), "up");

        getActionMap().put("up", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                snake.changeDirection(0, -CELL_SIZE);
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("DOWN"), "down");

        getActionMap().put("down", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                snake.changeDirection(0, CELL_SIZE);
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("LEFT"), "left");

        getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                snake.changeDirection(-CELL_SIZE, 0);
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke("RIGHT"), "right");

        getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                snake.changeDirection(CELL_SIZE, 0);
            }
        });
    }

    private void moveFood() {
        int x;
        int y;

        do {
            x = (int) (Math.random() * (BOARD_WIDTH / CELL_SIZE))
                    * CELL_SIZE;

            // לא מאפשרים אוכל בשורה העליונה שבה נמצא הניקוד
            y = ((int) (Math.random() * ((BOARD_HEIGHT / CELL_SIZE) - 2)) + 2)
                    * CELL_SIZE;

        } while (snake.containsPosition(new Point(x, y)));

        food.setPosition(x, y);

        System.out.println("New food: " + x + ", " + y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 25);

        snake.draw(g);
        food.draw(g);

        if (gameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", 180, 300);
        }

    }
}