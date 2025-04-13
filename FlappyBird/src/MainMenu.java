import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame {

    public static String playerName = "Player";

    public MainMenu() {
        setTitle("Flappy Bird - Main Menu");
        setSize(360, 640);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Load background
        Image bg = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        Image logoImg = new ImageIcon(getClass().getResource("assets/logo.png")).getImage();
        Image startImg = new ImageIcon(getClass().getResource("assets/start.png")).getImage();

        // Resize logo & start button with original aspect ratio
        Image logoScaled = logoImg.getScaledInstance(200, 90, Image.SCALE_SMOOTH);
        Image startScaled = startImg.getScaledInstance(150, 70, Image.SCALE_SMOOTH);

        // Background panel
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);
        setContentPane(panel);

        // Gambar Logo
        JLabel logoLabel = new JLabel(new ImageIcon(logoScaled));
        logoLabel.setBounds((360 - 200) / 2, 60, 200, 90);
        panel.add(logoLabel);

        // Input nama lebih estetik
        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.BOLD, 18));
        nameField.setBounds(70, 180, 220, 40);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setOpaque(false); // transparan
        nameField.setForeground(Color.WHITE);
        nameField.setCaretColor(Color.WHITE);
        nameField.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        nameField.setToolTipText("Enter your name");
        panel.add(nameField);

        // Tombol Start (gambar)
        JLabel startLabel = new JLabel(new ImageIcon(startScaled));
        startLabel.setBounds((360 - 150) / 2, 280, 150, 70);
        startLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(startLabel);

        startLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String input = nameField.getText().trim();
                if (!input.isEmpty()) {
                    playerName = input;
                }

                dispose(); // Tutup menu

                JFrame gameFrame = new JFrame("Flappy Bird - " + playerName);
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setSize(360, 640);
                gameFrame.setLocationRelativeTo(null);
                gameFrame.setResizable(false);

                FlappyBird gamePanel = new FlappyBird();
                gameFrame.add(gamePanel);
                gameFrame.pack();
                gamePanel.requestFocus();
                gameFrame.setVisible(true);
            }
        });
    }
}
