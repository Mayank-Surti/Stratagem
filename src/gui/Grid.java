package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import game.Game;
import game.Map;
import game.State;
import game.Map.Tile;
import game.State.BattleState;
import game.State.MainState;
import game.State.PrepState;
import game.State.Turn;

public class Grid {

    /* Attributes */
    private final static int WIDTH = MainFrame.HEIGHT;
    private final static int HEIGHT = MainFrame.HEIGHT;

    private Scene scene;
    private Game game;
    private Map map;
    public boolean[][] spots;

    /* Constructor */
    public Grid(Scene scene, Game game) {
    	this.scene = scene;
        this.game = game;
        this.map = game.map;
        spots = new boolean[Game.GRID_LENGTH][Game.GRID_LENGTH];
        resetSpots();
    }
    
    public void resetSpots() {
    	for (int i = 0; i < spots.length; i++) {
    		for (int j = 0; j < spots[0].length; j++) {
    			spots[i][j] = false;
    		}
    	}
    }
    
    public void findPath(int x, int y, int range) {
    	if (x < spots.length && y < spots[0].length && x >= 0 && y >= 0 && range >= 0) {
    		spots[x][y] = true;
    		range--;
    		findPath(x - 1, y, range);
    		findPath(x + 1, y, range);
    		findPath(x, y + 1, range);
    		findPath(x, y - 1, range);
    	}
    }

    /* Update and render */
    public void update() {

    }
    public void draw(Graphics2D g2D) {
    	int spriteX, spriteY;
        g2D.setStroke(new BasicStroke(5));
        for (int x = 0; x < map.sprites.length; x++) {
            for (int y = 0; y < map.sprites[0].length; y++) {
            	// draw map
            	spriteX = map.spriteSize * x + (MainFrame.WIDTH - MainFrame.HEIGHT);
            	spriteY = map.spriteSize * y;
                g2D.drawImage(map.sprites[x][y], spriteX, spriteY, map.spriteSize, map.spriteSize, null);
                // draw units
            	if (game.blueUnits[x][y] != null) {
                    g2D.drawImage(game.blueUnits[x][y].getSprite(), spriteX, spriteY, map.spriteSize, map.spriteSize, null);
                }
            	if (game.redUnits[x][y] != null) {
                    g2D.drawImage(game.redUnits[x][y].getSprite(), spriteX, spriteY, map.spriteSize, map.spriteSize, null);
                }
                // draw walk/attack range
            	if ((State.battle == BattleState.MOVE || State.battle == BattleState.ATTACK) && spots[x][y]) {
                    g2D.setColor(Color.BLUE);
            		g2D.drawRect(spriteX, spriteY, map.spriteSize, map.spriteSize);
            	}
                // draw selection box
                if (State.prep == PrepState.GRID || State.main == MainState.BATTLE) {
                	if (scene.getSelectionX() == (x + 1) && scene.getSelectionY() == (y + 1)) {
                        g2D.setColor(Color.RED);
                    	g2D.drawRect(spriteX, spriteY, map.spriteSize, map.spriteSize);
                    }	
                }
            }
        }
    }
    
}
