/* Mayank Surti
 * Grid Class - Handles displaying the map and the units
 * 5/31
 */

package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

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
    private final int SPRITE_OFFSET = 5, RECT_ARC = 10, RECT_STROKE = 6, RECT_OFFSET = 5;
    private final Color RECT_COLOR = new Color(0, 0, 0, MainPanel.TRANSPARENCY);
    private final Color BLUE = new Color(41, 174, 252, MainPanel.TRANSPARENCY);
    private final Color RED = new Color(230, 30, 28, MainPanel.TRANSPARENCY);

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

    /* Reset spots that the user can move/attack on */
    public void resetSpots() {
        for (int i = 0; i < spots.length; i++) {
            for (int j = 0; j < spots[0].length; j++) {
                spots[i][j] = false;
            }
        }
    }

    /* Recursive method to find the appropriate path with given range */
    public void findPath(int x, int y, int range) {
        if (x < spots.length && y < spots[0].length && x >= 0 && y >= 0 && range >= 0) {
            if ((State.battle == BattleState.MOVE && (map.tiles[x][y] != Tile.LAKE) &&
                    ((State.turn == Turn.BLUE && game.redUnits[x][y] == null) ||
                    (State.turn == Turn.RED && game.blueUnits[x][y] == null))) ||
                    State.battle == BattleState.ATTACK) {
                spots[x][y] = true;
                range--;
                findPath(x - 1, y, range);
                findPath(x + 1, y, range);
                findPath(x, y + 1, range);
                findPath(x, y - 1, range);
            }
        }
    }

   /* Render */
    public void draw(Graphics2D g2D) {
        int spriteX, spriteY, spriteSize, rectX, rectY, rectSize;
        g2D.setColor(RECT_COLOR);
        g2D.setStroke(new BasicStroke(RECT_STROKE));
        for (int x = 0; x < map.sprites.length; x++) {
            for (int y = 0; y < map.sprites[0].length; y++) {
                // initialize location and size variables
                spriteX = Map.SPRITE_SIZE * x + (MainFrame.WIDTH - MainFrame.HEIGHT);
                spriteY = Map.SPRITE_SIZE * y;
                spriteSize = Map.SPRITE_SIZE;
                rectX = spriteX + RECT_OFFSET;
                rectY = spriteY + RECT_OFFSET;
                rectSize = Map.SPRITE_SIZE - RECT_OFFSET * 2;
                // map
                g2D.drawImage(map.sprites[x][y], spriteX, spriteY, spriteSize, spriteSize, null);
                // available placement spots during prep
                if (State.main == MainState.PREP && State.prep == PrepState.GRID) {
                    g2D.setColor(RECT_COLOR);
                    if (State.turn == Turn.BLUE && y < Game.MAX_PLACE_SPACE) {
                        g2D.fillRoundRect(rectX, rectY, rectSize, rectSize, RECT_ARC, RECT_ARC);
                    } else if (State.turn == Turn.RED && y >= Game.GRID_LENGTH - Game.MAX_PLACE_SPACE) {
                        g2D.fillRoundRect(rectX, rectY, rectSize, rectSize, RECT_ARC, RECT_ARC);
                    }
                }
                // walk/attack range
                if (spots[x][y]) {
                    if (State.battle == BattleState.MOVE) {
                        g2D.setColor(BLUE);
                        g2D.fillRoundRect(rectX, rectY, rectSize, rectSize, RECT_ARC, RECT_ARC);
                    } else if (State.battle == BattleState.ATTACK) {
                        g2D.setColor(RED);
                        g2D.fillRoundRect(rectX, rectY, rectSize, rectSize, RECT_ARC, RECT_ARC);
                    }
                }
                // units
                spriteX += SPRITE_OFFSET;
                spriteY += SPRITE_OFFSET;
                spriteSize -= SPRITE_OFFSET * 2;
                if (game.blueUnits[x][y] != null) {
                    if (game.blueUnitsDone.contains(game.blueUnits[x][y].getName())) {
                        g2D.drawImage(game.blueUnits[x][y].getGraySprite(), spriteX, spriteY, spriteSize, spriteSize,
                                null);
                    } else {
                        g2D.drawImage(game.blueUnits[x][y].getColorSprite(), spriteX, spriteY, spriteSize, spriteSize,
                                null);
                    }
                }
                if (game.redUnits[x][y] != null) {
                    if (game.redUnitsDone.contains(game.redUnits[x][y].getName())) {
                        g2D.drawImage(game.redUnits[x][y].getGraySprite(), spriteX, spriteY, spriteSize, spriteSize,
                                null);
                    } else {
                        g2D.drawImage(game.redUnits[x][y].getColorSprite(), spriteX, spriteY, spriteSize, spriteSize,
                                null);
                    }
                }
                // selection box
                if (State.prep == PrepState.GRID || State.main == MainState.BATTLE) {
                    if (scene.getSelectionX() == (x + 1) && scene.getSelectionY() == (y + 1)) {
                        g2D.setColor(RECT_COLOR);
                        g2D.drawRoundRect(rectX, rectY, rectSize, rectSize, RECT_ARC, RECT_ARC);
                    }
                }
            }
        }
    }

}
