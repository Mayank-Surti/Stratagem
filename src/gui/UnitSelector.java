/* Mayank Surti
 * UnitSelector Class - To be used by PreparationScene; handles selectable units and their buttons
 * 5/31
 */

package gui;

import java.awt.Graphics2D;
import java.util.ArrayList;

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
    private final int TXT_OFFSET = 45, BTN_OFFSET = 50, BTN_ARC = 20, NEW_LINE_HEIGHT = 20;
    private final int BTN_WIDTH = (WIDTH - (TXT_OFFSET * 3)) / 2, BTN_HEIGHT = 40;

    public final int MAX_GOOD_UNITS = 3, MAX_GREAT_UNITS = 2;
    public int goodUnitsRem = 0, greatUnitsRem = 0;
    private PreparationScene prepScene;
    public Unit[][] availableUnits;
    public Button[][] unitButtons;
    public ArrayList<Unit> usedUnits = new ArrayList<Unit>();

    /* Constructor */
    public UnitSelector(PreparationScene parent) {
        prepScene = parent;
        resetAvailableUnits(Turn.BLUE);
        initButtons();
    }

    /* Reset base units */
    public void resetAvailableUnits(Turn team) {
        availableUnits = new Unit[2][5];
        availableUnits[0][0] = new Mercenary(team);
        availableUnits[1][0] = new Hero(team);
        availableUnits[0][1] = new Knight(team);
        availableUnits[1][1] = new Paladin(team);
        availableUnits[0][2] = new Myrmidon(team);
        availableUnits[1][2] = new Swordmaster(team);
        availableUnits[0][3] = new Archer(team);
        availableUnits[1][3] = new Sniper(team);
        availableUnits[0][4] = new Mage(team);
        availableUnits[1][4] = new Wizard(team);
    }

    /* Initialize Unit buttons */
    public void initButtons() {
        unitButtons = new Button[2][5];
        int gap = 20;
        for (int i = 0; i < availableUnits[0].length; i++) {
            unitButtons[0][i] = new Button(availableUnits[0][i].getName(), TXT_OFFSET,
                    Y + TXT_OFFSET * 2 + gap + BTN_OFFSET * i, BTN_WIDTH, BTN_HEIGHT, BTN_ARC);
        }
        for (int i = 0; i < availableUnits[0].length; i++) {
            unitButtons[1][i] = new Button(availableUnits[1][i].getName(), TXT_OFFSET * 2 + BTN_WIDTH,
                    Y + TXT_OFFSET * 2 + gap + BTN_OFFSET * i, BTN_WIDTH, BTN_HEIGHT, BTN_ARC);
        }
    }

    /* Disable appropriate buttons if all good/great units have been chosen */
    public void checkButtonsStatus() {
        if (goodUnitsRem >= MAX_GOOD_UNITS) {
            disableButtons(0);
        }
        if (greatUnitsRem >= MAX_GREAT_UNITS) {
            disableButtons(1);
        }
    }

    /* Disables unusable column of buttons */
    private void disableButtons(int col) {
        for (int i = 0; i < availableUnits[0].length; i++) {
            unitButtons[col][i].setDisabled(true);
        }
    }

    /* Render */
    public void draw(Graphics2D g2D) {
        // rect
        g2D.setColor(MainPanel.DARK_BLUE);
        g2D.fillRect(0, Y, WIDTH, HEIGHT);
        // instructions to choose unit
        g2D.setColor(MainPanel.BROWN1);
        g2D.setFont(MainPanel.mainFont.deriveFont(MainPanel.HEADER_SIZE));
        g2D.drawString(State.turn.name() + " Team, choose a unit.", TXT_OFFSET, Y + TXT_OFFSET);
        g2D.setFont(MainPanel.mainFont.deriveFont(MainPanel.NORMAL_SIZE));
        g2D.drawString("Good Units (" + goodUnitsRem + "/" + MAX_GOOD_UNITS + "):", TXT_OFFSET, Y + TXT_OFFSET * 2);
        g2D.drawString("Great Units (" + greatUnitsRem + "/" + MAX_GREAT_UNITS + "):", TXT_OFFSET * 2 + BTN_WIDTH,
                Y + TXT_OFFSET * 2);
        // unit buttons
        for (int i = 0; i < availableUnits.length; i++) {
            for (int j = 0; j < availableUnits[0].length; j++) {
                if (State.prep == PrepState.UNIT && prepScene.selectionX == (i + 1)
                        && prepScene.selectionY == (j + 1)) {
                    unitButtons[i][j].setSelected(true);
                    g2D.setColor(MainPanel.BROWN1);
                    MainPanel.drawFormattedString(availableUnits[i][j].getDesc(), TXT_OFFSET, MainFrame.HEIGHT -
                            TXT_OFFSET - NEW_LINE_HEIGHT, NEW_LINE_HEIGHT, g2D);
                } else {
                    unitButtons[i][j].setSelected(false);
                }
                unitButtons[i][j].draw(g2D);
            }
        }

    }

}
