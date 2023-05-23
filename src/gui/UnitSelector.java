package gui;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Game;
import game.State;
import game.State.PrepState;
import game.State.Turn;
import game.Units.*;

public class UnitSelector {

    /* Attributes */
    private final int WIDTH = MainFrame.WIDTH - MainFrame.HEIGHT;
    private final int HEIGHT = MainFrame.HEIGHT - (MainFrame.HEIGHT / Game.GRID_LENGTH) * 2;
    private final int Y = MainFrame.HEIGHT - HEIGHT;
    private final int BTN_OFFSET = 55;
    private final int BTN_HEIGHT = 50;
    
    private PreparationScene prepScene;
    public Unit[] availableUnits;
    private Button[] unitButtons;

    /* Constructor */
    public UnitSelector(PreparationScene parent) {
        prepScene = parent;
        resetAvailableUnits(Turn.BLUE);
        initButtons();
    }

    /* Initialize Unit buttons */
    public void initButtons() {
        unitButtons = new Button[availableUnits.length];
        for (int i = 0; i < unitButtons.length; i++) {
            unitButtons[i] = new Button(availableUnits[i].getName(), BTN_OFFSET, 
                Y + BTN_OFFSET + BTN_HEIGHT + BTN_OFFSET * i, WIDTH - (BTN_OFFSET * 2), BTN_HEIGHT);
        }
    }

    /* Reset base units */
    public void resetAvailableUnits(Turn team) {
        availableUnits = new Unit[2];
        availableUnits[0] = new Knight(team);
        availableUnits[1] = new Archer(team);
    }

    /* Update and render */
    public void update() {

    }
    public void draw(Graphics2D g2D) {
        g2D.setColor(Color.LIGHT_GRAY);
        g2D.fillRect(0, Y, WIDTH, HEIGHT);

        g2D.setColor(Color.BLACK);
        g2D.drawString(State.turn.name() + " Team, choose a unit:", BTN_OFFSET, Y + BTN_OFFSET);

        for (int i = 0; i < unitButtons.length; i++) {
            if (State.prep == PrepState.UNIT && prepScene.selectionY == (i + 1)) {
                unitButtons[i].setIsSelected(true);
            } else {
                unitButtons[i].setIsSelected(false);
            }
            unitButtons[i].draw(g2D);
        }
    }
    
}
