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
    private final int TXT_OFFSET = 45, BTN_OFFSET = 10, BTN_WIDTH = 200, BTN_HEIGHT = 40;

    private PreparationScene prepScene;
    public Unit[][] availableUnits;
    private Button[][] unitButtons;

    /* Constructor */
    public UnitSelector(PreparationScene parent) {
        prepScene = parent;
        resetAvailableUnits(Turn.BLUE);
        initButtons();
    }

    /* Reset base units */
    public void resetAvailableUnits(Turn team) {
        availableUnits = new Unit[2][3];
        availableUnits[0][0] = new Knight(team);	availableUnits[1][0] = new Paladin(team);
        availableUnits[0][1] = new Archer(team);	availableUnits[1][1] = new Sniper(team);
        availableUnits[0][2] = new Mage(team);		availableUnits[1][2] = new Wizard(team);
    }
    
    /* Initialize Unit buttons */
    public void initButtons() {
        unitButtons = new Button[2][3];
        
        for (int i = 0; i < 3; i++) {
        	unitButtons[0][i] = new Button(availableUnits[0][i].getName(), TXT_OFFSET,
        			Y + BTN_OFFSET * 2 + (BTN_HEIGHT + BTN_OFFSET) * (i + 1), BTN_WIDTH, BTN_HEIGHT);
        }
        for (int i = 0; i < 3; i++) {
        	unitButtons[1][i] = new Button(availableUnits[1][i].getName(), TXT_OFFSET * 2 + BTN_WIDTH,
        			Y + BTN_OFFSET * 2 + (BTN_HEIGHT + BTN_OFFSET) * (i + 1), BTN_WIDTH, BTN_HEIGHT);
        }
    }

    /* Update and render */
    public void update() {

    }
    public void draw(Graphics2D g2D) {
        g2D.setColor(Color.LIGHT_GRAY);
        g2D.fillRect(0, Y, WIDTH, HEIGHT);

        g2D.setColor(Color.BLACK);
        g2D.setFont(MainPanel.mainFont);
        g2D.drawString(State.turn.name() + " Team, choose a unit:", TXT_OFFSET, Y + TXT_OFFSET);

        for (int i = 0; i < 3; i++) {
            if (State.prep == PrepState.UNIT && prepScene.selectionX == 1 && prepScene.selectionY == (i + 1)) {
                unitButtons[0][i].setIsSelected(true);
            } else {
                unitButtons[0][i].setIsSelected(false);
            }
            unitButtons[0][i].draw(g2D);
        }
        for (int i = 0; i < 3; i++) {
            if (State.prep == PrepState.UNIT && prepScene.selectionX == 2 && prepScene.selectionY == (i + 1)) {
                unitButtons[1][i].setIsSelected(true);
            } else {
                unitButtons[1][i].setIsSelected(false);
            }
            unitButtons[1][i].draw(g2D);
        }
        
//        for (int i = 0; i < unitButtons.length; i++) {
//        	for (int j = 0; j < unitButtons[0].length; j++) {
//        		if (State.prep == PrepState.UNIT && prepScene.selectionY == (i + 1)) {
//                  unitButtons[i][j].setIsSelected(true);
//              } else {
//                  unitButtons[i][j].setIsSelected(false);
//              }
//              unitButtons[0][i].draw(g2D);
//        	}
//        }
    }
    
}
