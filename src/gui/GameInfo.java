/* Mayank Surti
 * GameInfo Class - Displays instructions and current turn
 * 5/31
 */

package gui;

import java.awt.Graphics2D;

import game.Game;
import game.State;
import game.State.BattleState;
import game.State.MainState;
import game.State.Turn;

public class GameInfo {

    /* Attributes */
    private final int WIDTH = MainFrame.WIDTH - MainFrame.HEIGHT;
    private final int HEIGHT = (MainFrame.HEIGHT / Game.GRID_LENGTH) * 2;
    private final int OFFSET = 40, NEW_LINE_HEIGHT = 20;

    private Game game;

    /* Constructor */
    public GameInfo(Game game) {
        this.game = game;
    }

    /* Render */
    public void draw(Graphics2D g2D) {
        // rect
        g2D.setColor(MainPanel.BROWN2);
        g2D.fillRect(0, 0, WIDTH, HEIGHT);
        // current phase
        g2D.setColor(MainPanel.DARK_BLUE);
        g2D.setFont(MainPanel.mainFont.deriveFont(MainPanel.HEADER_SIZE));
        if (State.main == MainState.PREP) {
            g2D.drawString("Preparation Phase", OFFSET, OFFSET);
        } else if (State.main == MainState.BATTLE) {
            if (State.battle != BattleState.END) {
                g2D.drawString("Battle Phase", OFFSET, OFFSET);
            } else {
                g2D.drawString("Game Complete", OFFSET, OFFSET);
            }
        }
        // current turn
        if (State.main == MainState.BATTLE && State.battle != BattleState.END) {
            g2D.drawString(
                    "Current Turn: " + State.turn.name() + ", " + (game.getTeamSize(State.turn) - game.moves)
                            + " moves left. ",
                    OFFSET, OFFSET * 2);
        } else if (State.main == MainState.PREP) {
            g2D.drawString("Current Turn: " + State.turn.name(), OFFSET, OFFSET * 2);
        }

        // instructions
        g2D.setFont(MainPanel.mainFont.deriveFont(MainPanel.NORMAL_SIZE));
        switch (State.main) {
            default:
                break;
            case PREP:
                switch (State.prep) {
                    case UNIT:
                        g2D.drawString("Choose a unit to be on your team.", OFFSET, OFFSET * 3);
                        break;
                    case GRID:
                        g2D.drawString("Select a highlighted location to place that unit.", OFFSET, OFFSET * 3);
                        break;
                }
                break;
            case BATTLE:
                switch (State.battle) {
                    case UNIT:
                        g2D.drawString("Choose a unit to move.", OFFSET, OFFSET * 3);
                        break;
                    case MOVE:
                        MainPanel.drawFormattedString(
                                "Select a highlighted location within the unit's walk \nrange to move the chosen unit.",
                                OFFSET, OFFSET * 3, NEW_LINE_HEIGHT, g2D);
                        break;
                    case ATTACK:
                        MainPanel.drawFormattedString(
                                "Select an enemy to attack. If no enemies are in \nthe attack range, hit enter anywhere.",
                                OFFSET, OFFSET * 3, NEW_LINE_HEIGHT, g2D);
                        break;
                    case END:
                        g2D.setFont(MainPanel.mainFont.deriveFont(MainPanel.HEADER_SIZE));
                        if (game.isTeamDead(Turn.BLUE)) {
                            g2D.drawString("RED team has won!", OFFSET, OFFSET * 2);
                        } else {
                            g2D.drawString("BLUE team has won!", OFFSET, OFFSET * 2);
                        }
                        g2D.setFont(MainPanel.mainFont.deriveFont(MainPanel.NORMAL_SIZE));
                        g2D.drawString("Press enter to return to main menu.", OFFSET, HEIGHT - OFFSET / 2);
                        break;
                }
                if (State.battle != BattleState.END) {
                    g2D.drawString("Press spacebar to end your turn early.", OFFSET, HEIGHT - OFFSET / 2);
                }
                break;
        }
    }
}
