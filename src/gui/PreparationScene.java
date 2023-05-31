/* Mayank Surti
 * PreparationScene Class - Handles preparation phase of the game including selecting and placing units
 * 5/31
 */

package gui;

import javax.swing.JPanel;
import java.awt.Graphics2D;

import game.Game;
import game.State;
import game.State.PrepState;
import game.State.Turn;

public class PreparationScene extends Scene {

	/* Attributes */
	private MainPanel mainPane;
	private Game game;
	private UnitSelector unitSelector;
	private GameInfo gameInfo;
	private Grid grid;
	private UnitInfo unitInfo;
	private int newUnitX = 0, newUnitY;

	/* Constructor */
	public PreparationScene(JPanel mainPane) {
		super(mainPane);
		this.mainPane = (MainPanel) mainPane;
		this.game = this.mainPane.game;
		unitSelector = new UnitSelector(this);
		selectionY = 1;
		grid = new Grid(this, this.game);
		unitInfo = new UnitInfo();
		gameInfo = new GameInfo(game);
		resetSelection(unitSelector.availableUnits.length, unitSelector.availableUnits[0].length);
	}

	/* Input */
	@Override
	protected void upPressed() {
		decrementSelectionY();
	}
	@Override
	protected void downPressed() {
		incrementSelectionY();
	}
	protected void leftPressed() {
		decrementSelectionX();
	}
	@Override
	protected void rightPressed() {
		incrementSelectionX();
	}
	private int counter = 0;
	@Override
	protected void enterPressed() {
		switch (State.prep) {
			case UNIT: // select which unit to place
				newUnitX = selectionX - 1;
				newUnitY = selectionY - 1;
				if (!unitSelector.unitButtons[newUnitX][newUnitY].isDisabled()) {
					if (!unitSelector.availableUnits[newUnitX][newUnitY].wasPromoted()) {
						if (unitSelector.goodUnitsRem < unitSelector.MAX_GOOD_UNITS) {
							unitSelector.unitButtons[newUnitX][newUnitY].setDisabled(true);
							State.prep = PrepState.GRID;
							resetSelection(Game.GRID_LENGTH, Game.GRID_LENGTH);
						}
					} else {
						if (unitSelector.greatUnitsRem < unitSelector.MAX_GREAT_UNITS) {
							unitSelector.unitButtons[newUnitX][newUnitY].setDisabled(true);
							State.prep = PrepState.GRID;
							resetSelection(Game.GRID_LENGTH, Game.GRID_LENGTH);
						}
					}
				}
				break;
			case GRID: // select where to place chosen unit
				switch (State.turn) {
					case BLUE: // blue team places unit
						if (game.getUnit(Turn.BLUE, selectionX - 1, selectionY - 1) == null
								&& game.isValidTile(selectionX - 1, selectionY - 1) &&
								selectionY <= Game.MAX_PLACE_SPACE) {
							unitSelector.resetAvailableUnits(Turn.BLUE);
							game.addUnit(Turn.BLUE, unitSelector.availableUnits[newUnitX][newUnitY], selectionX - 1,
									selectionY - 1);
							if (!game.blueUnits[selectionX - 1][selectionY - 1].wasPromoted()) {
								unitSelector.goodUnitsRem++;
							} else {
								unitSelector.greatUnitsRem++;
							}
							unitSelector.checkButtonsStatus();
							counter++;
							if (counter >= Game.NUM_UNITS) {
								unitSelector.goodUnitsRem = 0;
								unitSelector.greatUnitsRem = 0;
								counter = 0;
								unitSelector.initButtons();
								State.turn = Turn.RED;
							}
							lastX = selectionX;
							lastY = selectionY;
							State.prep = PrepState.UNIT;
							resetSelection(unitSelector.availableUnits.length, unitSelector.availableUnits[0].length);
						}
						break;
					case RED: // red team places unit
						if (game.getUnit(Turn.RED, selectionX - 1, selectionY - 1) == null
								&& game.isValidTile(selectionX - 1, selectionY - 1) &&
								selectionY > Game.GRID_LENGTH - Game.MAX_PLACE_SPACE) {
							unitSelector.resetAvailableUnits(Turn.RED);
							game.addUnit(Turn.RED, unitSelector.availableUnits[newUnitX][newUnitY], selectionX - 1,
									selectionY - 1);
							if (!game.redUnits[selectionX - 1][selectionY - 1].wasPromoted()) {
								unitSelector.goodUnitsRem++;
							} else {
								unitSelector.greatUnitsRem++;
							}
							unitSelector.checkButtonsStatus();
							lastX = selectionX;
							lastY = selectionY;
							counter++;
							if (counter >= Game.NUM_UNITS) {
								mainPane.initBattle();
								counter = 0;
							}
							State.prep = PrepState.UNIT;
							resetSelection(unitSelector.availableUnits.length, unitSelector.availableUnits[0].length);
						}
						break;
				}
				break;
		}
	}
	@Override
	protected void backPressed() {
		if (State.prep == PrepState.GRID) {
			State.prep = PrepState.UNIT;
			unitSelector.unitButtons[newUnitX][newUnitY].setDisabled(false);
			resetSelection(unitSelector.availableUnits.length, unitSelector.availableUnits[0].length);
		}
	}

	/* Render */
	@Override
	protected void draw(Graphics2D g2D) {
		grid.draw(g2D);

		switch (State.prep) {
			case UNIT:
				unitSelector.draw(g2D);
				break;
			case GRID:
				unitInfo.setUnit(unitSelector.availableUnits[newUnitX][newUnitY]);
				unitInfo.draw(g2D);
				break;
		}

		gameInfo.draw(g2D);
	}

}
