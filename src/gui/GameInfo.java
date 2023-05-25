package gui;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Game;
import game.State;
import game.State.BattleState;
import game.State.Turn;

public class GameInfo {
    
    /* Attributes */
    private final int WIDTH = MainFrame.WIDTH - MainFrame.HEIGHT;
    private final int HEIGHT = (MainFrame.HEIGHT / Game.GRID_LENGTH) * 2;
    private final int OFFSET = 45, NEW_LINE_HEIGHT = 20;

    private Game game;

    /* Constructor */
    public GameInfo(Game game) {
        this.game = game;
    }

    /* Render */
    public void draw(Graphics2D g2D) {
        g2D.setColor(Color.GRAY);
        g2D.fillRect(0, 0, WIDTH, HEIGHT);

        g2D.setColor(Color.BLACK);
        g2D.setFont(MainPanel.mainFont);
        if (State.battle != BattleState.END) {
            g2D.drawString("Current Turn: " + State.turn.name(), OFFSET, OFFSET);
        }

        switch (State.main) {
            case PREP:
            switch (State.prep) {
                case UNIT:
                g2D.drawString("Choose a unit to be on your team.", OFFSET, OFFSET * 2);
                break;
                case GRID:
                g2D.drawString("Select a location to place that unit.", OFFSET, OFFSET * 2);
                break;
            }
            break;
            case BATTLE:
            switch (State.battle) {
                case UNIT:
                g2D.drawString("Choose a unit to move.", OFFSET, OFFSET * 2);
                break;
                case MOVE:
                g2D.drawString("Select a location within the unit's walk ", OFFSET, OFFSET * 2);
                g2D.drawString("range to move the chosen unit.", OFFSET, OFFSET * 2 + NEW_LINE_HEIGHT);
                break;
                case ATTACK:
                g2D.drawString("Select an enemy unit to attack. If no enemies ", OFFSET, OFFSET * 2);
                g2D.drawString("are in the attack range, hit enter.", OFFSET, OFFSET * 2 + NEW_LINE_HEIGHT);
                break;
                case END:
                if (game.isTeamDead(Turn.BLUE)) {
                    g2D.drawString("RED team has won!", OFFSET, OFFSET);
                } else {
                    g2D.drawString("BLUE team has won!", OFFSET, OFFSET);
                }
                g2D.drawString("Press enter to start a new game.", OFFSET, OFFSET * 2);
                break;
            }
            break;
        }
    }
}
