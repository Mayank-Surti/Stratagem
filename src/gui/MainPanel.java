/* Mayank Surti
 * Game Class - Handles game related data including unit positions, statuses, levels, and more
 * 5/31
 */

package gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;

import game.Game;
import game.Map;
import game.State;
import game.State.*;

public class MainPanel extends JPanel implements Runnable {

	/* Attributes */
	private Thread gameThread;
	private Scene prepScene, battleScene, menuScene;
	public Game game;

	public static Font mainFont;
	public static final float NORMAL_SIZE = 25, HEADER_SIZE = 30, HEADER2_SIZE = 50, TITLE_SIZE = 100;
	public static final int TRANSPARENCY = 127;
	public static Color DARK_BLUE = new Color(74, 83, 102);
	public static Color BLUE_BROWN = new Color(155, 156, 161);
	public static Color BROWN1 = new Color(235, 229, 219);
	public static Color BROWN2 = new Color(232, 215, 183);
	public static Color BROWN3 = new Color(190, 159, 91);
	public static Color BROWN4 = new Color(82, 70, 58);

	/* Constructor */
	public MainPanel() {
		this.setDoubleBuffered(true);
		this.setLayout(null);
		try {
			mainFont = Font.createFont(Font.TRUETYPE_FONT, new File("other/RPGSystem.ttf"));
			mainFont = mainFont.deriveFont(NORMAL_SIZE);
		} catch (Exception e) {
			mainFont = new Font("Serif", Font.TRUETYPE_FONT, 22);
		}
		initMenu();
	}

	/* Initialize Menu/Preparation/Battle Scene */
	public void initMenu() {
		menuScene = new MenuScene(this);
		State.main = MainState.MENU;
		State.menu = MenuState.OPTIONS;
		prepScene = null;
		battleScene = null;
	}
	public void initPrep(Map map) {
		game = new Game(map);
		prepScene = new PreparationScene(this);
		State.main = MainState.PREP;
		State.turn = Turn.BLUE;
		State.prep = PrepState.UNIT;
		menuScene = null;
		battleScene = null;
	}
	public void initBattle() {
		battleScene = new BattleScene(this);
		State.main = MainState.BATTLE;
		State.turn = Turn.BLUE;
		State.battle = BattleState.UNIT;
		menuScene = null;
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

	/* Draw string that starts a new line when where '\n' is found */
	public static void drawFormattedString(String text, int x, int y, int gap, Graphics2D g2D) {
		int start = 0, end = 0, counter = 0;
		for (int i = 0; i < text.length(); i++) {
			if (i < text.length() && text.substring(i, i + 1).equals("\n") || i == text.length() - 1) {
				end = i + 1;
				g2D.drawString(text.substring(start, end), x, y + gap * counter);
				counter++;
				start = end;
			}
		}
	}

	/* Paint/render game visuals (called by game thread) */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;

		switch (State.main) {
			case MENU:
				menuScene.draw(g2D);
				break;
			case PREP:
				prepScene.draw(g2D);
				break;
			case BATTLE:
				battleScene.draw(g2D);
				break;

		}

		g2D.dispose();
	}

}