public class App {
    public static void main(String[] args) {
        // Tampilkan main menu terlebih dahulu
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }
}
