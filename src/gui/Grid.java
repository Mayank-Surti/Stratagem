package gui;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;

import game.Game;
import game.Map.Tile;

public class Grid {

    /* Attributes */
    private final static int WIDTH = MainFrame.HEIGHT;
    private final static int HEIGHT = MainFrame.HEIGHT;

    private Game game;

    /* Constructor */
    public Grid(Game game) {
        this.game = game;
    }

    /* Update and render */
    public void update() {

    }
    public void draw(Graphics2D g2D) {
        for (int x = 0; x < game.map.sprites.length; x++) {
            for (int y = 0; y < game.map.sprites[0].length; y++) {
                g2D.drawImage(game.map.sprites[x][y], 
                game.map.spriteSize * x + (MainFrame.WIDTH - MainFrame.HEIGHT), 
                game.map.spriteSize * y, 
                    game.map.spriteSize, game.map.spriteSize, null);
            }
        }
    }
    
}
