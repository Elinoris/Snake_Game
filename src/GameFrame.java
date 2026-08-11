
import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        setTitle("Snake");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        showStartScreen();

        setVisible(true);
    }

    public void showStartScreen() {
        setContentPane(new StartPanel(this));
        revalidate();
        repaint();
    }

    public void showInstructions() {
        setContentPane(new InstructionsPanel(this));
        revalidate();
        repaint();
    }
    public void startGame() {
        setContentPane(new GamePanel());
        revalidate();
        repaint();
    }
}