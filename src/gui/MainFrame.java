/* Mayank Surti
 * MainFrame Class - Handles data relating to window size, name, and other properties
 * 5/31
 */

package gui;

import javax.swing.JFrame;
import java.awt.Dimension;

import game.Game;
import game.Map;

public class MainFrame extends JFrame {

    /* Attributes */
    public final static String GAME_NAME = "Game Name";
    public final static int WIDTH = 1280;
    public final static int HEIGHT = Game.GRID_LENGTH * Map.SPRITE_SIZE;
    public final static int FPS = 60;

    /* Constructor */
    public MainFrame() {
        this.setTitle(GAME_NAME);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);
        this.setLayout(null);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainPanel mainPane = new MainPanel();
        this.add(mainPane);
        mainPane.setBounds(0, 0, WIDTH, HEIGHT);
        mainPane.startGameThread();

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}