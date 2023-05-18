package gui;

import game.Game;
import game.State;
import game.State.BattleState;
import game.State.Turn;

import javax.swing.*;

import java.awt.*;

public class BattleScene extends Scene {
	
	/* Attributes */
	private MainPanel mainPane;
	private Grid grid;
	
	private int currentX, currentY;

    /* Constructor */
    public BattleScene(JPanel mainPane) {
        super(mainPane);
        this.mainPane = (MainPanel) mainPane;
        maxSelection = Game.GRID_LENGTH;
        selectionX = 1;
        selectionY = 1;
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
		switch (State.battle) {
		case GRID:	// select unit on the grid
			switch (State.turn) {
			case BLUE:	// blue team selects a unit
				if (mainPane.game.blueUnits[selectionX - 1][selectionY - 1] != null) {
					currentX = selectionX - 1;
					currentY = selectionY - 1;
					grid.resetSpots();
					grid.findPath(currentX, currentY, mainPane.game.blueUnits[currentX][currentY].getWalkRange());
					State.battle = BattleState.MOVE;
				} else {
					
				}
				break;
			case RED: // red team selects a unit
				if (mainPane.game.redUnits[selectionX - 1][selectionY - 1] != null) {
					currentX = selectionX - 1;
					currentY = selectionY - 1;
					grid.resetSpots();
					grid.findPath(currentX, currentY, mainPane.game.redUnits[currentX][currentY].getWalkRange());
					State.battle = BattleState.MOVE;
				} else {

				}
				break;
		}
		break;
		case MOVE:	// move unit to selected location
			if (grid.spots[selectionX - 1][selectionY - 1]) {
				switch (State.turn) {
					case BLUE: 	// move blue unit
						mainPane.game.moveUnit(Turn.BLUE, currentX, currentY, selectionX - 1, selectionY - 1); 
						currentX = selectionX - 1;
						currentY = selectionY - 1;
						grid.resetSpots();
						grid.findPath(currentX, currentY, mainPane.game.blueUnits[currentX][currentY].getAttackRange());
						break;
					case RED: 	// move red unit
						mainPane.game.moveUnit(Turn.RED, currentX, currentY, selectionX - 1, selectionY - 1); 
						currentX = selectionX - 1;
						currentY = selectionY - 1;
						grid.resetSpots();
						grid.findPath(currentX, currentY, mainPane.game.redUnits[currentX][currentY].getAttackRange());
						break;
					}
			}
			State.battle = BattleState.ATTACK;
			break;
		case ATTACK:	// attack enemy unit with current selected unit
			if (grid.spots[selectionX - 1][selectionY - 1]) {
				switch (State.turn) {
				case BLUE:	// blue unit attacks red unit
					if (mainPane.game.redUnits[selectionX - 1][selectionY - 1] != null) {
						mainPane.game.blueUnits[currentX][currentY].attackUnit(mainPane.game.redUnits[selectionX - 1][selectionY - 1]);
					}
					State.turn = Turn.RED;
					break;
				case RED:	// red unit attacks blue unit
					if (mainPane.game.blueUnits[selectionX - 1][selectionY - 1] != null) {
						mainPane.game.redUnits[currentX][currentY].attackUnit(mainPane.game.blueUnits[selectionX - 1][selectionY - 1]);
					}
					State.turn = Turn.BLUE;
					break;
				}
			State.battle = BattleState.GRID;
			break;
			}
		}
	}
	
	/* Update and render */
    @Override
    protected void update() {
    }

    @Override
    protected void draw(Graphics2D g2D) {
    	grid.draw(g2D);
    }
    
}
