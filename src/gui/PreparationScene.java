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
			State.prep = PrepState.GRID; 
			resetSelection(8);
			break;
			case GRID: 
			resetSelection(2);
			switch (State.turn) {
				case BLUE:
				mainPane.game.blueTeam.addUnit(unitSelector.AVAIL_UNITS[selectionY - 1], selectionX - 1, selectionY - 1);
				if (mainPane.game.blueTeam.isFull()) {
					State.turn = Turn.RED;
				} else {
					State.prep = PrepState.UNIT;
				}
				break;
				case RED: 
				mainPane.game.redTeam.addUnit(unitSelector.AVAIL_UNITS[selectionY - 1], selectionX - 1, selectionY - 1);
				if (mainPane.game.redTeam.isFull()) {
					//
				}
				break;
			}
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
