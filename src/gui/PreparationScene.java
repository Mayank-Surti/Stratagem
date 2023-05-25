package gui;

import java.awt.*;

import javax.swing.*;

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
	private int newUnitIndex = 0;

	/* Constructor */
	public PreparationScene(JPanel mainPane) {
		super(mainPane);
		this.mainPane = (MainPanel) mainPane;
		this.game = this.mainPane.game;
		unitSelector = new UnitSelector(this);
		maxSelection = unitSelector.availableUnits.length;
		selectionY = 1;
		grid = new Grid(this, this.game);
		unitInfo = new UnitInfo();
		gameInfo = new GameInfo(game);
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
	
	int counter = 0;
	@Override
	protected void enterPressed() {
		switch (State.prep) {
			case UNIT: 	// select which unit to place
			newUnitIndex = selectionY - 1;
			State.prep = PrepState.GRID; 
			resetSelection(Game.GRID_LENGTH);
			break;
			case GRID:	// select where to place chosen unit
			switch (State.turn) {
				case BLUE:	// blue team places unit
					if (game.getUnit(Turn.BLUE, selectionX - 1, selectionY - 1) == null && selectionY <= Game.MAX_PLACE_SPACE) {
						unitSelector.resetAvailableUnits(Turn.BLUE);
						game.addUnit(Turn.BLUE, unitSelector.availableUnits[newUnitIndex], selectionX - 1, selectionY - 1);
						counter++;
						if (counter >= Game.NUM_UNITS) {
							State.turn = Turn.RED;
							counter = 0;
						}
						State.prep = PrepState.UNIT;
						resetSelection(unitSelector.availableUnits.length);
					}
					break;
				case RED: 	// red team places unit
					if (game.getUnit(Turn.RED, selectionX - 1, selectionY - 1) == null && selectionY > Game.GRID_LENGTH - Game.MAX_PLACE_SPACE) {
						unitSelector.resetAvailableUnits(Turn.RED);
						game.addUnit(Turn.RED, unitSelector.availableUnits[newUnitIndex], selectionX - 1, selectionY - 1);
						counter++;
						if (counter >= Game.NUM_UNITS) {
							mainPane.initBattle();
							counter = 0;
						}
						State.prep = PrepState.UNIT;
						resetSelection(unitSelector.availableUnits.length);
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
			resetSelection(unitSelector.availableUnits.length);
		}
	}

	/* Update and render */
	@Override
	protected void update() {
		
	}
	@Override
	protected void draw(Graphics2D g2D) {
		grid.draw(g2D);

		switch (State.prep) {
			case UNIT:
			unitSelector.draw(g2D);
			break;
			case GRID:
			unitInfo.setUnit(unitSelector.availableUnits[newUnitIndex]);
			unitInfo.draw(g2D);
			break;
		}

		gameInfo.draw(g2D);
	}

}
