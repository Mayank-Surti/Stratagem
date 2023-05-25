package game;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.ImageIcon;

import gui.MainFrame;

public class Map {

    public enum Tile{
        FLOOR, LAKE, FORT;
    }

    /* Attributes */
    private char[][] data;
    public Image[][] sprites;
    public Tile[][] tiles;
    public final int spriteSize = MainFrame.HEIGHT / Game.GRID_LENGTH;

    /* Constructor */
    public Map(File file) {

        try {
            FileReader in = new FileReader(file);
            BufferedReader readFile = new BufferedReader(in);

            data = new char[Game.GRID_LENGTH][Game.GRID_LENGTH];
            sprites = new Image[Game.GRID_LENGTH][Game.GRID_LENGTH];
            tiles = new Tile[Game.GRID_LENGTH][Game.GRID_LENGTH];

            for (int x = 0; x < Game.GRID_LENGTH; x++) {
				for (int y = 0; y < Game.GRID_LENGTH; y++) {
					data[y][x] = (char) readFile.read();
				}
				readFile.readLine();
			}

            readFile.close();
            in.close();
        } catch (Exception e) {
            
        }

        setUpMap();
    }

    /* Set up map sprites according to file data */ 
    private void setUpMap() {
        for (int x = 0; x < Game.GRID_LENGTH; x++) {
            for (int y = 0; y < Game.GRID_LENGTH; y++) {
                switch (data[x][y]) {
                    default:
                    case '0':
                    sprites[x][y] = new ImageIcon("sprites/map/grass.png").getImage();
                    tiles[x][y] = Tile.FLOOR;
                    break;
                    case '1':
                    sprites[x][y] = new ImageIcon("sprites/map/fort.png").getImage();
                    tiles[x][y] = Tile.FORT;
                    break;
                    case '2':
                    sprites[x][y] = new ImageIcon("sprites/map/lake.png").getImage();
                    tiles[x][y] = Tile.LAKE;
                    break;
                    case '3':
                    sprites[x][y] = new ImageIcon("sprites/map/bridge.png").getImage();
                    tiles[x][y] = Tile.FLOOR;
                    break;
                }
            }
        }
    }

    public Tile getTile(int x, int y) {
    	return tiles[x][y];
    }
    
}
