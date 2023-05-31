/* Mayank Surti
 * UnitInfo Class - Displays a particular unit's properties
 * 5/31
 */

package gui;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Game;
import game.State;
import game.State.MainState;
import game.Units.Unit;

public class UnitInfo {

    /* Attributes */
    private final int WIDTH = MainFrame.WIDTH - MainFrame.HEIGHT;
    private final int HEIGHT = MainFrame.HEIGHT - (MainFrame.HEIGHT / Game.GRID_LENGTH) * 2;
    private final int Y = MainFrame.HEIGHT - HEIGHT;
    private final int CARD_WIDTH = 245, CARD_HEIGHT = 220;
    private final int OFFSET = 40, STROKE = 10, ARC = 20;
    private final Color BLUE = new Color(41, 174, 252, MainPanel.TRANSPARENCY);
    private final Color RED = new Color(230, 30, 28, MainPanel.TRANSPARENCY);
    private Unit unit;

    /* Constructor */
    public UnitInfo() {
        unit = null;
    }

    /* Getter and Setter */
    public Unit getUnit() {
        return unit;
    }
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /* Render */
    public void draw(Graphics2D g2D) {
        // rect
        g2D.setColor(MainPanel.DARK_BLUE);
        g2D.fillRect(0, Y, WIDTH, HEIGHT);
        // unit properties and card
        if (unit != null) {
            g2D.setColor(MainPanel.BROWN1);
            g2D.setFont(MainPanel.mainFont.deriveFont(MainPanel.HEADER_SIZE));
            if (State.main == MainState.PREP || unit.wasPromoted()) {
                g2D.drawString(unit.getName(), OFFSET, Y + OFFSET);
            } else if (State.main != MainState.PREP && !unit.wasPromoted()) {
                g2D.drawString(
                        unit.getName() + " - Attacks to level up: " + unit.getNumAttacks() + "/" + Unit.ATTACKS_TO_PRO,
                        OFFSET, Y + OFFSET);
            }
            g2D.setFont(MainPanel.mainFont.deriveFont(MainPanel.NORMAL_SIZE));
            g2D.drawString("HP: " + unit.getHP() + "/" + unit.getMaxHP(), OFFSET, Y + OFFSET * 2);
            g2D.drawString("Damage: " + unit.getDMG(), OFFSET, Y + OFFSET * 3);
            g2D.drawString("Walk Range: " + unit.getWalkRange(), OFFSET, Y + OFFSET * 4);
            g2D.drawString("Attack Range: " + unit.getAttackRange(), OFFSET, Y + OFFSET * 5);
            if (unit.getCard() != null) {
                if (State.main != MainState.PREP) {
                    switch (unit.getTeam()) {
                        case BLUE:
                            g2D.setColor(BLUE);
                            break;
                        case RED:
                            g2D.setColor(RED);
                            break;
                    }
                    g2D.fillRoundRect(OFFSET - STROKE, MainFrame.HEIGHT - OFFSET - CARD_HEIGHT - STROKE,
                            CARD_WIDTH + STROKE * 2, CARD_HEIGHT + STROKE * 2, ARC, ARC);
                }
                g2D.drawImage(unit.getCard(), OFFSET, MainFrame.HEIGHT - CARD_HEIGHT - OFFSET,
                        CARD_WIDTH, CARD_HEIGHT, null);
            }
        }
    }
}
