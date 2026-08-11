
import javax.swing.*;
import java.awt.*;

public class InstructionsPanel extends JPanel {

    private GameFrame frame;

    public InstructionsPanel(GameFrame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());

        JLabel title = new JLabel("הוראות המשחק", SwingConstants.CENTER);

        JTextArea instructions = new JTextArea();
        instructions.setText(
                "מטרת המשחק:\n" +
                        "לאכול את האוכל ולצבור כמה שיותר נקודות.\n\n" +
                        "שליטה:\n" +
                        "חץ למעלה - תנועה למעלה\n" +
                        "חץ למטה - תנועה למטה\n" +
                        "חץ שמאלה - תנועה שמאלה\n" +
                        "חץ ימינה - תנועה ימינה\n\n" +
                        "אין להתנגש בקירות או בגוף הנחש."
        );

        instructions.setEditable(false);

        JButton backButton = new JButton("חזרה");

        add(title, BorderLayout.NORTH);
        add(instructions, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        backButton.addActionListener(e -> frame.showStartScreen());
    }
}