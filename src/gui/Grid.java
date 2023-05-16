package gui;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import game.Game;
import game.Map;
import game.State;
import game.Map.Tile;
import game.State.BattleState;
import game.State.PrepState;

public class Grid {

    /* Attributes */
    private final static int WIDTH = MainFrame.HEIGHT;
    private final static int HEIGHT = MainFrame.HEIGHT;

    private Scene scene;
    private Game game;
    private Map map;

    /* Constructor */
    public Grid(Scene scene, Game game) {
    	this.scene = scene;
        this.game = game;
        this.map = game.map;
        
        try {
        	scene = (PreparationScene) scene;
        } catch (Exception e) {
        	scene = (BattleScene) scene;
        }
    }

    /* Update and render */
    public void update() {

    }
    public void draw(Graphics2D g2D) {
    	int spriteX, spriteY;
        for (int x = 0; x < map.sprites.length; x++) {
            for (int y = 0; y < map.sprites[0].length; y++) {
            	// draw map
            	spriteX = map.spriteSize * x + (MainFrame.WIDTH - MainFrame.HEIGHT);
            	spriteY = map.spriteSize * y;
                g2D.drawImage(map.sprites[x][y], spriteX, spriteY, map.spriteSize, map.spriteSize, null);
                // draw units
                for (int i = 0; i < 8; i++) {
                	if (game.blueTeam.getUnit(i) != null) {
                		System.out.println(game.blueTeam.getUnit(i).getName());
                		g2D.drawImage(game.blueTeam.getUnit(i).getSprite(), 
                				map.spriteSize * game.blueTeam.getUnit(i).getX() + (MainFrame.WIDTH - MainFrame.HEIGHT), 
                				map.spriteSize * game.blueTeam.getUnit(i).getY(), 
                				map.spriteSize, map.spriteSize, null);
                	}
                }
                // draw selection box
                if (State.prep == PrepState.GRID || State.battle == BattleState.GRID) {
                	if (scene.getSelectionX() == (x + 1) && scene.getSelectionY() == (y + 1)) {
                    	g2D.drawRect(spriteX, spriteY, map.spriteSize, map.spriteSize);
                    }	
                }
            }
        }
    }
    
}
