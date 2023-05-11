package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainPanel extends JPanel implements Runnable {
	
	private Thread gameThread;

	/* Constructor */
	public MainPanel() {
		this.setDoubleBuffered(true);
		this.setLayout(null);
	}
	
	/* Game thread and loop */
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	public void run() {
		long now;
		long updateTime;
		long wait;
		final long OPTIMAL_TIME = 1000000000 / MainFrame.FPS;
		while (gameThread != null) {
			now = System.nanoTime();
			update();
			repaint();
			updateTime = System.nanoTime() - now;
			wait = (OPTIMAL_TIME - updateTime) / 1000000;
			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/* Update game info (called by game thread) */
	protected void update() {
	}

	/* Paint/render game visuals (called by game thread) */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		
		ImageIcon imageIcon = new ImageIcon("sprites/blue-knight.png");
		Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		g2D.drawImage(image, 20, 20, null);
		
		g2D.dispose();
	}

}