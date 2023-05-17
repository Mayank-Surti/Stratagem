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
	private Turn currentTurn;

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
					currentTurn = Turn.BLUE;
					State.battle = BattleState.ACTION;
				} else {
					System.out.println("js");
				}
				break;
			case RED:	// red team selects a unit
				break;
			}
			break;
		case ACTION:
			grid.initSpots();
			grid.findPath(currentX, currentY, mainPane.game.blueUnits[currentX][currentY].getWalkRange());
			
			break;
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
