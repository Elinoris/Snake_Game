import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Snake snake;
    private Food food;
    private Thread gameThread;

    private int score = 0;
    private int level = 1;
    private int gameSpeed = 150;

    private boolean gameOver = false;

    private final int CELL_SIZE = 25;
    private final int BOARD_WIDTH = 600;
    private final int BOARD_HEIGHT = 600;
    private final int HEADER_HEIGHT = 50;

    public GamePanel() {
        setBackground(new Color(18, 18, 18));//יצירת קוביות לתחושבת התמצות במרחב
        setPreferredSize(new Dimension(600, 650));

        snake = new Snake();
        food = new Food(300, 100);

        setupKeyBindings();

       resetGame();
    }

    // =========================
    // רמות קושי
    // =========================

    private void updateLevel() {
        if (score < 50) {
            level = 1;
            gameSpeed = 150;
        } else if (score < 100) {
            level = 2;
            gameSpeed = 125;
        } else if (score < 200) {
            level = 3;
            gameSpeed = 100;
        } else if (score < 400) {
            level = 4;
            gameSpeed = 75;
        } else if (score < 500) {
            level = 5;
            gameSpeed = 50;
        }
        else {
            level = 6;
            gameSpeed = 10;
        }
    }

//יציאת אוכל חדש

    private void moveFood() {
        int x;
        int y;

        do {
            x = (int) (Math.random() * (BOARD_WIDTH / CELL_SIZE)) * CELL_SIZE;

            y = (int) (Math.random() * ((BOARD_HEIGHT - HEADER_HEIGHT) / CELL_SIZE)) * CELL_SIZE + HEADER_HEIGHT;

        } while (snake.containsPosition(new Point(x, y)));

        food.setPosition(x, y);
    }

//מקשים
    private void setupKeyBindings() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");

        getActionMap().put("up", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                snake.changeDirection(0, -CELL_SIZE);
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down");

        getActionMap().put("down", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                snake.changeDirection(0, CELL_SIZE);
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "left");

        getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                snake.changeDirection(-CELL_SIZE, 0);
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "right");

        getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                snake.changeDirection(CELL_SIZE, 0);
            }
        });
        // מקש SPACE לריסטארט
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "restart");

        getActionMap().put("restart", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (gameOver) {
                    resetGame();
                }
            }
        });
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;


        g2.setColor(new Color(18, 18, 18));
        g2.fillRect(0, HEADER_HEIGHT, BOARD_WIDTH, BOARD_HEIGHT);


        g2.setColor(new Color(30, 30, 30));
        g2.fillRect(0, 0, BOARD_WIDTH, HEADER_HEIGHT);

        // קו הפרדה
        g2.setColor(new Color(70, 70, 70));
        g2.fillRect(0, HEADER_HEIGHT - 1, BOARD_WIDTH, 1);

        // משבצות
        drawGrid(g2);

        // Score
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.drawString("Score: " + score, 20, 32);

        // Level
        g2.drawString("Level: " + level, 500, 32);

        // נחש
        snake.draw(g2);

        // אוכל
        food.draw(g2);

        // Game Over
        if (gameOver) {
            drawGameOver(g2);
        }
    }



    private void drawGrid(Graphics2D g2) {
        g2.setColor(new Color(25, 25, 25));

        for (int x = 0; x <= BOARD_WIDTH; x += CELL_SIZE) {
            g2.drawLine(x, HEADER_HEIGHT, x, HEADER_HEIGHT + BOARD_HEIGHT);
        }

        for (int y = HEADER_HEIGHT; y <= HEADER_HEIGHT + BOARD_HEIGHT; y += CELL_SIZE) {
            g2.drawLine(0, y, BOARD_WIDTH, y);
        }
    }



    private void drawGameOver(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 170));
        g2.fillRect(0, HEADER_HEIGHT, BOARD_WIDTH, BOARD_HEIGHT);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 50));

        String gameOverText = "GAME OVER";
        FontMetrics fm = g2.getFontMetrics();
        int textX = (BOARD_WIDTH - fm.stringWidth(gameOverText)) / 2;

        g2.drawString(gameOverText, textX, 290);

        g2.setFont(new Font("Arial", Font.BOLD, 24));

        String scoreText = "Final Score: " + score;
        fm = g2.getFontMetrics();
        int scoreX = (BOARD_WIDTH - fm.stringWidth(scoreText)) / 2;

        g2.drawString(scoreText, scoreX, 335);
        // הודעה לשחקן
        g2.setFont(new Font("Arial", Font.PLAIN, 18));
        g2.setColor(Color.YELLOW);

        String restartText = "Press SPACE to Restart";
        fm = g2.getFontMetrics();
        int restartX = (BOARD_WIDTH - fm.stringWidth(restartText)) / 2;

        g2.drawString(restartText, restartX, 380);
    }

    private void resetGame() {

        score = 0;
        level = 1;
        gameSpeed = 150;
        gameOver = false;


        snake = new Snake();
        moveFood();


        startGameThread();
    }

    private void startGameThread() {
        gameThread = new Thread(() -> {
            while (!gameOver) {

                if (!snake.move(BOARD_WIDTH, BOARD_HEIGHT-25, HEADER_HEIGHT)) {
                    gameOver = true;
                }

                if (snake.isEating(food)) {
                    snake.grow();
                    score += 10;
                    updateLevel();
                    moveFood();
                }

                if (snake.hitItself()) {
                    gameOver = true;
                }

                repaint();

                try {
                    Thread.sleep(gameSpeed);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        gameThread.start();
    }


    }

