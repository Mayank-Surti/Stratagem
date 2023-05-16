package gui;

import java.awt.Color;
import java.awt.Graphics2D;

import game.State;
import game.State.PrepState;
import game.Units.*;

public class UnitSelector {

    /* Attributes */
    private final static int WIDTH = MainFrame.WIDTH - MainFrame.HEIGHT;
    private final static int HEIGHT = MainFrame.HEIGHT;
    private final static int BTN_OFFSET = 55;
    private final static int BTN_HEIGHT = 50;
    
    private PreparationScene prepScene;
    public final Unit[] AVAIL_UNITS = { new Knight(), new Archer() };
    private Button[] unitButtons;

    /* Constructor */
    public UnitSelector(PreparationScene parent) {
        prepScene = parent;
        initButtons();
    }

    /* Initialize Unit buttons */
    public void initButtons() {
        unitButtons = new Button[AVAIL_UNITS.length];
        for (int i = 0; i < unitButtons.length; i++) {
            unitButtons[i] = new Button(AVAIL_UNITS[i].getName(), BTN_OFFSET, BTN_HEIGHT + BTN_OFFSET * i, WIDTH - (BTN_OFFSET * 2), BTN_HEIGHT);
        }
    }

    /* Update and render */
    public void update() {

    }
    public void draw(Graphics2D g2D) {
        g2D.setColor(Color.LIGHT_GRAY);
        g2D.fillRect(0, 0, WIDTH, HEIGHT);

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
