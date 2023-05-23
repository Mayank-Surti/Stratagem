package gui;

import game.Game;
import game.State;
import game.State.BattleState;
import game.State.Turn;
import game.Units.Knight;
import game.Units.Paladin;

import javax.swing.*;

import java.awt.*;

public class BattleScene extends Scene {
	
	/* Attributes */
	private MainPanel mainPane;
	private Game game;
	private Grid grid;
	private UnitInfo unitInfo;
	private GameInfo gameInfo;
	private int currentX, currentY, oldX, oldY;

    /* Constructor */
    public BattleScene(JPanel mainPane) {
        super(mainPane);
        this.mainPane = (MainPanel) mainPane;
		this.game = this.mainPane.game;
        maxSelection = Game.GRID_LENGTH;
        selectionX = 1;
        selectionY = 1;
        grid = new Grid(this, this.mainPane.game);
		unitInfo = new UnitInfo();
		gameInfo = new GameInfo(game);
    }

	/* Check if hovering over unit */
	private void checkHoveringUnit() {
		if (game.blueUnits[selectionX - 1][selectionY - 1] != null) {
			unitInfo.setUnit(game.blueUnits[selectionX - 1][selectionY - 1]);
		} else if (game.redUnits[selectionX - 1][selectionY - 1] != null) {
			unitInfo.setUnit(game.redUnits[selectionX - 1][selectionY - 1]);
		} else {
			unitInfo.setUnit(null);
		}
	}
    
    /* Input */
	@Override
	protected void upPressed() {
		decrementSelectionY();
		checkHoveringUnit();
	}
	@Override
	protected void downPressed() {
		incrementSelectionY();
		checkHoveringUnit();
	}
	protected void leftPressed() {
		decrementSelectionX();
		checkHoveringUnit();
	}
	@Override
	protected void rightPressed() {
		incrementSelectionX();
		checkHoveringUnit();
	}
	@Override
	protected void enterPressed() {
		switch (State.battle) {
		case UNIT:	// select unit on the grid
			switch (State.turn) {
			case BLUE:	// blue team selects a unit
				if (game.blueUnits[selectionX - 1][selectionY - 1] != null) {
					currentX = selectionX - 1;
					currentY = selectionY - 1;
					grid.resetSpots();
					grid.findPath(currentX, currentY, game.blueUnits[currentX][currentY].getWalkRange());
					State.battle = BattleState.MOVE;
				}
				break;
			case RED: // red team selects a unit
				if (game.redUnits[selectionX - 1][selectionY - 1] != null) {
					currentX = selectionX - 1;
					currentY = selectionY - 1;
					grid.resetSpots();
					grid.findPath(currentX, currentY, game.redUnits[currentX][currentY].getWalkRange());
					State.battle = BattleState.MOVE;
				}
				break;
		}
		break;
		case MOVE:	// move unit to selected location
			if (grid.spots[selectionX - 1][selectionY - 1] && 
			(game.blueUnits[selectionX - 1][selectionY - 1] == null || 
				game.blueUnits[selectionX - 1][selectionY - 1] == game.blueUnits[currentX][currentY]) &&
			(game.redUnits[selectionX - 1][selectionY - 1] == null || 
				game.redUnits[selectionX - 1][selectionY - 1] == game.redUnits[currentX][currentY])) {
				switch (State.turn) {
					case BLUE: 	// move blue unit
						game.moveUnit(Turn.BLUE, currentX, currentY, selectionX - 1, selectionY - 1); 
						oldX = currentX;
						oldY = currentY;
						currentX = selectionX - 1;
						currentY = selectionY - 1;
						grid.resetSpots();
						grid.findPath(currentX, currentY, game.blueUnits[currentX][currentY].getAttackRange());
						break;
					case RED: 	// move red unit
						game.moveUnit(Turn.RED, currentX, currentY, selectionX - 1, selectionY - 1); 
						oldX = currentX;
						oldY = currentY;
						currentX = selectionX - 1;
						currentY = selectionY - 1;
						grid.resetSpots();
						grid.findPath(currentX, currentY, game.redUnits[currentX][currentY].getAttackRange());
						break;
					}
				State.battle = BattleState.ATTACK;
			}
			break;
		case ATTACK:	// attack enemy unit with current selected unit
			if (grid.spots[selectionX - 1][selectionY - 1]) {
				switch (State.turn) {
				case BLUE:	// blue unit attacks red unit
					if (game.redUnits[selectionX - 1][selectionY - 1] != null) {
						game.blueUnits[currentX][currentY].attackUnit(game.redUnits[selectionX - 1][selectionY - 1]);
						game.checkDeadUnit(Turn.RED, selectionX - 1, selectionY - 1);
					}
					State.turn = Turn.RED;
					break;
				case RED:	// red unit attacks blue unit
					if (game.blueUnits[selectionX - 1][selectionY - 1] != null) {
						game.redUnits[currentX][currentY].attackUnit(game.blueUnits[selectionX - 1][selectionY - 1]);
						game.checkDeadUnit(Turn.BLUE, selectionX - 1, selectionY - 1);
					}
					State.turn = Turn.BLUE;
					break;
				}
				if (game.isTeamDead(Turn.BLUE) || game.isTeamDead(Turn.RED)) {
					unitInfo.setUnit(null);
					State.battle = BattleState.END;
				} else {
					unitInfo.setUnit(null);
					State.battle = BattleState.UNIT;
				}
			} else {
				switch (State.turn) {
					case BLUE: State.turn = Turn.RED; break;
					case RED: State.turn = Turn.BLUE; break;
				}
				State.battle = BattleState.UNIT;
			}
			break;
		case END:
			State.battle = null;
			mainPane.initPrep();
			break;
		}
	}
	@Override
	protected void backPressed() {
		if (State.battle == BattleState.MOVE) {				// retirn to select unit from location
			State.battle = BattleState.UNIT;
		} else if (State.battle == BattleState.ATTACK) {	// return to select location from attack
			switch (State.turn) {
				case BLUE:
				game.moveUnit(Turn.BLUE, currentX, currentY, oldX, oldY);
				currentX = oldX;
				currentY = oldY;
				grid.resetSpots();
				grid.findPath(currentX, currentY, game.blueUnits[currentX][currentY].getWalkRange());
				State.battle = BattleState.MOVE;
				break;
				case RED:
				game.moveUnit(Turn.RED, currentX, currentY, oldX, oldY);
				currentX = oldX;
				currentY = oldY;
				grid.resetSpots();
				grid.findPath(currentX, currentY, game.redUnits[currentX][currentY].getWalkRange());
				State.battle = BattleState.MOVE;
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
		unitInfo.draw(g2D);
		gameInfo.draw(g2D);
    }
    
}
