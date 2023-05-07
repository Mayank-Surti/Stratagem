import java.awt.Dimension;
import javax.swing.*;

public class Main {

    public Main() {
        
        JFrame frame;
        JPanel contentPane;

        frame = new JFrame("Game");
        frame.setPreferredSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();

        frame.setContentPane(contentPane);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new Main();
    }

}