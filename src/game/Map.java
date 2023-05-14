package game;

import java.awt.Image;

import javax.swing.ImageIcon;

import gui.MainFrame;

public class Map {

    public enum Tile{
        FLOOR, FORT
    }

    /* Attributes */
    public Image[][] sprites;
    public Tile[][] tiles;
    public final int spriteSize = MainFrame.HEIGHT / 8;

    /* Constructor */
    public Map() {
        sprites = new Image[8][8];
        tiles = new Tile[8][8];
        for (int x = 0; x < sprites.length; x++) {
            for (int y = 0; y < sprites[0].length; y++) {
                sprites[x][y] = new ImageIcon("sprites/map/floor.png").getImage();
                tiles[x][y] = Tile.FLOOR;
            }
        }
    }
    
}
