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
    private final int OFFSET = 50;
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
        g2D.setColor(Color.LIGHT_GRAY);
        g2D.fillRect(0, Y, WIDTH, HEIGHT);
        if (unit != null) {
            g2D.setColor(Color.BLACK);
            g2D.drawString(unit.getName(), OFFSET, Y + OFFSET);
            g2D.drawString("HP: " + unit.getHP() + "/" + unit.getMaxHP(), OFFSET, Y + OFFSET * 2);
            g2D.drawString("Walk Range: " + unit.getWalkRange(), OFFSET, Y + OFFSET * 3);
            g2D.drawString("Attack Range: " + unit.getAttackRange(), OFFSET, Y + OFFSET * 4);
            if (State.main == MainState.BATTLE) {
                g2D.drawString(unit.getTeam().name(), OFFSET, MainFrame.HEIGHT - OFFSET);
            }
        }
    }
}
