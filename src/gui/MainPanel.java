package gui;

import javax.swing.*;

import game.Game;
import game.State;
import game.State.*;

import java.awt.*;

public class MainPanel extends JPanel implements Runnable {
	
	/* Attributes */
	private Thread gameThread;
	private Scene prepScene, battleScene;
	public Game game;
	
	/* Constructor */
	public MainPanel() {
		this.setDoubleBuffered(true);
		this.setLayout(null);
	
		initPrep();
	}

	/* Initialize Preparation/Battle Scene */
	public void initPrep() {
		game = new Game();
		prepScene = new PreparationScene(this);
		State.main = MainState.PREP;
		State.turn = Turn.BLUE;
		State.prep = PrepState.UNIT;
		battleScene = null;
	}
	public void initBattle() {
		battleScene = new BattleScene(this);
		State.main = MainState.BATTLE;
		State.turn = Turn.BLUE;
		State.battle = BattleState.UNIT;
		prepScene = null;
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
		
		switch (State.main) {
			case PREP: prepScene.update(); break;
			case BATTLE: battleScene.update(); break;
		}
		
	}

	/* Paint/render game visuals (called by game thread) */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		
		switch (State.main) {
			case PREP: prepScene.draw(g2D); break;
			case BATTLE: battleScene.draw(g2D); break;
			
		}
		
		g2D.dispose();
	}

}