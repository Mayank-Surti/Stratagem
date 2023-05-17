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
    public final int spriteSize = MainFrame.HEIGHT / Game.GRID_LENGTH;

    /* Constructor */
    public Map() {
        sprites = new Image[Game.GRID_LENGTH][Game.GRID_LENGTH];
        tiles = new Tile[Game.GRID_LENGTH][Game.GRID_LENGTH];
        for (int x = 0; x < sprites.length; x++) {
            for (int y = 0; y < sprites[0].length; y++) {
                sprites[x][y] = new ImageIcon("sprites/map/floor.png").getImage();
                tiles[x][y] = Tile.FLOOR;
            }
        }
    }
    
}
