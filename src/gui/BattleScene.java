/* Mayank Surti
 * BattleScene Class - Handles battle phase; selecting, moving, and attacking units
 * 5/31
 */

package gui;

import javax.swing.JPanel;
import java.awt.Graphics2D;

import game.Game;
import game.State;
import game.State.BattleState;
import game.State.Turn;
import game.Units.Unit;

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
		maxSelectionX = Game.GRID_LENGTH;
		maxSelectionY = Game.GRID_LENGTH;
		selectionX = 1;
		selectionY = 1;
		grid = new Grid(this, this.mainPane.game);
		unitInfo = new UnitInfo();
		gameInfo = new GameInfo(game);
	}

	/* Check if hovering over unit. If so, display unit info */
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
			case UNIT: // select unit on the grid
				switch (State.turn) {
					case BLUE: // blue team selects a unit
						if (game.blueUnits[selectionX - 1][selectionY - 1] != null &&
								!game.blueUnitsDone
										.contains(game.blueUnits[selectionX - 1][selectionY - 1].getName())) {
							currentX = selectionX - 1;
							currentY = selectionY - 1;
							State.battle = BattleState.MOVE;
							grid.resetSpots();
							grid.findPath(currentX, currentY, game.blueUnits[currentX][currentY].getWalkRange());
						}
						break;
					case RED: // red team selects a unit
						if (game.redUnits[selectionX - 1][selectionY - 1] != null &&
								!game.redUnitsDone.contains(game.redUnits[selectionX - 1][selectionY - 1].getName())) {
							currentX = selectionX - 1;
							currentY = selectionY - 1;
							State.battle = BattleState.MOVE;
							grid.resetSpots();
							grid.findPath(currentX, currentY, game.redUnits[currentX][currentY].getWalkRange());
						}
						break;
				}
				break;
			case MOVE: // move unit to selected location
				if (grid.spots[selectionX - 1][selectionY - 1] &&
						(game.blueUnits[selectionX - 1][selectionY - 1] == null ||
								game.blueUnits[selectionX - 1][selectionY - 1] == game.blueUnits[currentX][currentY])
						&&
						(game.redUnits[selectionX - 1][selectionY - 1] == null ||
								game.redUnits[selectionX - 1][selectionY - 1] == game.redUnits[currentX][currentY])
						&&
						game.isValidTile(selectionX - 1, selectionY - 1)) {
					switch (State.turn) {
						case BLUE: // move blue unit
							game.moveUnit(Turn.BLUE, currentX, currentY, selectionX - 1, selectionY - 1);
							oldX = currentX;
							oldY = currentY;
							currentX = selectionX - 1;
							currentY = selectionY - 1;
							State.battle = BattleState.ATTACK;
							grid.resetSpots();
							grid.findPath(currentX, currentY, game.blueUnits[currentX][currentY].getAttackRange());
							break;
						case RED: // move red unit
							game.moveUnit(Turn.RED, currentX, currentY, selectionX - 1, selectionY - 1);
							oldX = currentX;
							oldY = currentY;
							currentX = selectionX - 1;
							currentY = selectionY - 1;
							State.battle = BattleState.ATTACK;
							grid.resetSpots();
							grid.findPath(currentX, currentY, game.redUnits[currentX][currentY].getAttackRange());
							break;
					}
				}
				break;
			case ATTACK: // attack enemy unit with current selected unit
				switch (State.turn) {
					case BLUE: // blue unit attacks red unit
						if (grid.spots[selectionX - 1][selectionY - 1]
								&& game.redUnits[selectionX - 1][selectionY - 1] != null) {
							game.blueUnits[currentX][currentY]
									.attackUnit(game.redUnits[selectionX - 1][selectionY - 1]);
							if (game.blueUnits[currentX][currentY].getNumAttacks() >= Unit.ATTACKS_TO_PRO) {
								game.promoteUnit(Turn.BLUE, game.blueUnits[currentX][currentY].getType(), currentX,
										currentY);
							}
							game.checkDeadUnit(Turn.RED, selectionX - 1, selectionY - 1);
						}
						game.blueUnitsDone.add((game.blueUnits[currentX][currentY].getName()));
						game.incrementMoves(Turn.BLUE);
						break;
					case RED: // red unit attacks blue unit
						if (grid.spots[selectionX - 1][selectionY - 1]
								&& game.blueUnits[selectionX - 1][selectionY - 1] != null) {
							game.redUnits[currentX][currentY]
									.attackUnit(game.blueUnits[selectionX - 1][selectionY - 1]);
							if (game.redUnits[currentX][currentY].getNumAttacks() >= Unit.ATTACKS_TO_PRO) {
								game.promoteUnit(Turn.RED, game.redUnits[currentX][currentY].getType(), currentX,
										currentY);
							}
							game.checkDeadUnit(Turn.BLUE, selectionX - 1, selectionY - 1);
						}
						game.redUnitsDone.add((game.redUnits[currentX][currentY].getName()));
						game.incrementMoves(Turn.RED);
						break;
				}
				if (game.isTeamDead(Turn.BLUE) || game.isTeamDead(Turn.RED)) { // end game is units on a team are dead
					unitInfo.setUnit(null);
					State.battle = BattleState.END;
				} else {
					unitInfo.setUnit(null);
					State.battle = BattleState.UNIT;
				}
				game.checkForts();
				break;
			case END:
				State.battle = null;
				mainPane.initMenu();
				break;
		}
	}
	@Override
	protected void backPressed() {
		if (State.battle == BattleState.MOVE) { // return to select unit from location
			switch (State.turn) {
				case BLUE:
					game.blueUnitsDone.remove(game.blueUnits[currentX][currentY].getName());
					break;
				case RED:
					game.redUnitsDone.remove(game.redUnits[currentX][currentY].getName());
					break;
			}
			State.battle = BattleState.UNIT;
		} else if (State.battle == BattleState.ATTACK) { // return to select location from attack
			switch (State.turn) {
				case BLUE:
					game.moveUnit(Turn.BLUE, currentX, currentY, oldX, oldY);
					currentX = oldX;
					currentY = oldY;
					State.battle = BattleState.MOVE;
					grid.resetSpots();
					grid.findPath(currentX, currentY, game.blueUnits[currentX][currentY].getWalkRange());
					break;
				case RED:
					game.moveUnit(Turn.RED, currentX, currentY, oldX, oldY);
					currentX = oldX;
					currentY = oldY;
					State.battle = BattleState.MOVE;
					grid.resetSpots();
					grid.findPath(currentX, currentY, game.redUnits[currentX][currentY].getWalkRange());
					break;
			}
		}
	}
	@Override
	protected void spacePressed() {
		if (State.battle != BattleState.END) {	// end turn early
			switch (State.turn) {
				case BLUE:
					State.turn = Turn.RED;
					break;
				case RED:
					State.turn = Turn.BLUE;
					break;
			}
			game.resetMoves();
			game.blueUnitsDone.clear();
			game.redUnitsDone.clear();
			State.battle = BattleState.UNIT;
		}
	}

	/* Render */
	@Override
	protected void draw(Graphics2D g2D) {
		grid.draw(g2D);
		unitInfo.draw(g2D);
		gameInfo.draw(g2D);
	}

}
