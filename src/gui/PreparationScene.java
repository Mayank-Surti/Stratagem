package gui;

import java.awt.*;
import javax.swing.*;

import game.State;
import game.State.PrepState;
import game.State.Turn;

public class PreparationScene extends Scene {

	/* Attributes */
	private MainPanel mainPane;
	private UnitSelector unitSelector;
	private Grid grid;
	private int newUnitIndex = 0;

	/* Constructor */
	public PreparationScene(JPanel mainPane) {
		super(mainPane);
		this.mainPane = (MainPanel) mainPane;
		maxSelection = 2;
		selectionY = 1;
		unitSelector = new UnitSelector(this);
		grid = new Grid(this, this.mainPane.game);
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
	
	@Override
	protected void enterPressed() {
		switch (State.prep) {
			case UNIT: 
			newUnitIndex = selectionY - 1;
			System.out.println(newUnitIndex);
			State.prep = PrepState.GRID; 
			resetSelection(8);
			break;
			case GRID: 
			switch (State.turn) {
				case BLUE:
				mainPane.game.addUnit(Turn.BLUE, unitSelector.availableUnits[newUnitIndex], selectionX - 1, selectionY - 1);
				//System.out.println(mainPane.game.getUnit(Turn.BLUE, selectionX - 1, selectionY - 1).getName());
				// if (/* mainPane.game.blueTeam.isFull() */ mainPane.game.isTeamFull(Turn.BLUE)) {
				// 	State.turn = Turn.RED;
				// } else {
				// 	State.prep = PrepState.UNIT;
				// }
				break;
				case RED: 
				break;
			}
			resetSelection(2);
			State.prep = PrepState.UNIT; 
			break;
		}
	}

	/* Update and render */
	@Override
	protected void update() {
		
	}
	@Override
	protected void draw(Graphics2D g2D) {
		unitSelector.draw(g2D);
		grid.draw(g2D);

		switch (State.prep) {
			case UNIT: break;
			case GRID: break;
		}
	}
	
	

}
