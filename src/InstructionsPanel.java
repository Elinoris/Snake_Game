

import javax.swing.*;
import java.awt.*;

public class InstructionsPanel extends JPanel {

    private GameFrame frame;

    public InstructionsPanel(GameFrame frame) {
        this.frame = frame;

        setBackground(new Color(20, 20, 20));
        setLayout(new GridBagLayout());

        // פאנל מרכזי
        JPanel contentPanel = new JPanel();

        contentPanel.setBackground(new Color(35, 35, 35));

        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(30, 45, 30, 45)
        );

        contentPanel.setLayout(
                new BoxLayout(contentPanel, BoxLayout.Y_AXIS)
        );

        // כותרת
        JLabel title = new JLabel("הוראות המשחק");

        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // טקסט ההוראות
        JLabel instructions = new JLabel(
                "<html>" +
                        "<div style='text-align:right;'>" +

                        "<b>מטרת המשחק</b><br>" +
                        "לאכול את האוכל, לגדול ולצבור כמה שיותר נקודות.<br><br>" +

                        "<b>שליטה במשחק</b><br>" +
                        "↑ &nbsp; תנועה למעלה<br>" +
                        "↓ &nbsp; תנועה למטה<br>" +
                        "← &nbsp; תנועה שמאלה<br>" +
                        "→ &nbsp; תנועה ימינה<br><br>" +

                        "<b>ניקוד ורמות</b><br>" +
                        "כל אוכל שנאכל מוסיף 10 נקודות ומאריך את הנחש <br> " +
                        "ככל שהניקוד עולה, רמת הקושי עולה.<br><br>" +

                        "<b>שימו לב!</b><br>" +
                        "התנגשות בקירות או בגוף הנחש מביאה לפסילת המשחק." +

                        "</div>" +
                        "</html>"
        );

        instructions.setForeground(Color.WHITE);
        instructions.setFont(
                new Font("Arial", Font.PLAIN, 18)
        );

        instructions.setComponentOrientation(
                ComponentOrientation.RIGHT_TO_LEFT
        );

        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

        // כפתור חזרה
        JButton backButton = new JButton("חזרה");

        backButton.setPreferredSize(
                new Dimension(180, 45)
        );

        backButton.setMaximumSize(
                new Dimension(180, 45)
        );

        backButton.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        backButton.setFocusPainted(false);

        backButton.setBackground(
                new Color(60, 60, 60)
        );

        backButton.setForeground(Color.WHITE);

        backButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // הוספת הרכיבים לפאנל
        contentPanel.add(title);

        contentPanel.add(
                Box.createVerticalStrut(25)
        );

        contentPanel.add(instructions);

        contentPanel.add(
                Box.createVerticalStrut(25)
        );

        contentPanel.add(backButton);

        // מיקום הפאנל במרכז
        add(contentPanel);

        // חזרה למסך הראשי
        backButton.addActionListener(
                e -> frame.showStartScreen()
        );
    }
}

