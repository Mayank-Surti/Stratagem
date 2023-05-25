package gui;

import javax.swing.JFrame;
import java.awt.Dimension;

public class MainFrame extends JFrame {

    /* Attributes */
	public final static int WIDTH = 1280;
	public final static int HEIGHT = 720;
	public final static int FPS = 60;

    /* Constructor */
    public MainFrame() {
    	this.setTitle("Game");
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);
        this.setLayout(null);
        //this.setUndecorated(true);
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