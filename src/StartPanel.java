
import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

    private GameFrame frame;

    public StartPanel(GameFrame frame) {
        this.frame = frame;

        setBackground(new Color(20, 20, 20));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel title = new JLabel("SNAKE");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 50));

        JButton startButton = createButton("התחל משחק");
        JButton instructionsButton = createButton("הוראות");

        gbc.gridy = 0;
        add(title, gbc);

        gbc.gridy = 1;
        add(startButton, gbc);

        gbc.gridy = 2;
        add(instructionsButton, gbc);

        instructionsButton.addActionListener(e -> frame.showInstructions());

        startButton.addActionListener(e -> frame.startGame());
    }

    private JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setPreferredSize(new Dimension(220, 55));
        button.setFont(new Font("Arial", Font.BOLD, 20));

        button.setFocusPainted(false);
        button.setBackground(new Color(60, 60, 60));
        button.setForeground(Color.WHITE);

        return button;
    }
}