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
		maxSelectionIndex = 2;
		selectionIndex = 1;
		unitSelector = new UnitSelector(this);
		grid = new Grid(this.mainPane.game);
	}

	/* Input */
	@Override
	protected void upPressed() {
		switch (State.prep) {
			case UNIT: decrementSelection(); break;
			case GRID: break;
		}
	}
	@Override
	protected void downPressed() {
		switch (State.prep) {
			case UNIT: incrementSelection(); break;
			case GRID: break;
		}
	}
	@Override
	protected void enterPressed() {
		switch (State.prep) {
			case UNIT: 
			State.prep = PrepState.GRID; 
			break;
			case GRID: 
			switch (State.turn) {
				case BLUE:
				mainPane.game.blueTeam.addUnit(unitSelector.AVAIL_UNITS[selectionIndex - 1]);
				if (mainPane.game.blueTeam.isFull()) {
					State.turn = Turn.RED;
				} else {
					State.prep = PrepState.UNIT;
				}
				break;
				case RED: 
				mainPane.game.redTeam.addUnit(unitSelector.AVAIL_UNITS[selectionIndex - 1]);
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
