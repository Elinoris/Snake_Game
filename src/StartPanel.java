
import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

    private GameFrame frame;

    public StartPanel(GameFrame frame) {
        this.frame = frame;

        setLayout(new GridBagLayout());

        JButton startButton = new JButton("התחל משחק");
        JButton instructionsButton = new JButton("הוראות");

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(startButton);
        buttonsPanel.add(instructionsButton);

        add(buttonsPanel);

        instructionsButton.addActionListener(e -> frame.showInstructions());

        startButton.addActionListener(e -> frame.startGame());
    }
}